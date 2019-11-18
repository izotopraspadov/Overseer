package edu.guap.enclave.web.servlets;

import edu.guap.enclave.model.Email;
import edu.guap.enclave.model.Employee;
import edu.guap.enclave.model.Phone;
import edu.guap.enclave.model.Region;
import edu.guap.enclave.repository.EmployeeRepository;
import edu.guap.enclave.repository.RegionRepository;
import edu.guap.enclave.util.ValidationUtil;
import edu.guap.enclave.web.AbstractEmployeeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(value = AdminEmployeeController.URL)
public class AdminEmployeeController extends AbstractEmployeeController {

    static final String URL = "/admin/employees";

    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private RegionRepository regionRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String get(ModelMap model, @PathVariable("id") int id) {
        Employee employee = super.getWithSalaryAndPhonesAndEmails(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employeeProfile";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam() String id,
                         @RequestParam() String fullName,
                         @RequestParam() String password,
                         @RequestParam() String login,
                         @RequestParam() String address,
                         @RequestParam() String region,
                         @RequestParam(required = false) String phone1,
                         @RequestParam(required = false) String phone2,
                         @RequestParam(required = false) String phone3,
                         @RequestParam(required = false) String email1,
                         @RequestParam(required = false) String email2,
                         @RequestParam(required = false) String email3,
                         @RequestParam() String salary) {

        Employee employee = repository.getWithSalaryAndPhonesAndEmails(Integer.valueOf(id)).get();

        employee.setFullName(fullName);
        employee.setPassword(password);
        employee.setLogin(login);
        employee.setAddress(address);
        if (!region.equals(employee.getRegion().getTitle()))
            regionRepository.getByTitle(region).ifPresent(employee::setRegion);

        String[] phones = {phone1, phone2, phone3};
        int count = 0;
        for (Phone phone : employee.getPhones()) {
            if (phones[count] != null
                    && !phone.getNumber().equals(phones[count])
                    && ValidationUtil.isValidPhoneLength(phones[count]))
                phone.setNumber(phones[count]);
            count++;
        }
        String[] emails = {email1, email2, email3};
        count = 0;
        for (Email email : employee.getEmails()) {
            if (emails[count] != null && !email.getEmail().equals(emails[count])) email.setEmail(emails[count]);
            count++;
        }
        if (ValidationUtil.isBigDecimal(salary))
            employee.getSalary().stream().findFirst().get().setAmount(new BigDecimal(salary));

        repository.save(employee, employee.getRegion().getId());

        return "redirect:/employees/switch/cmp";
    }

}
