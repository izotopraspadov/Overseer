package edu.guap.enclave.web.servlets;

import edu.guap.enclave.model.Employee;
import edu.guap.enclave.web.AbstractEmployeeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = EmployeeController.URL)
public class EmployeeController extends AbstractEmployeeController {
    static final String URL = "/employees";

    @RequestMapping(value = "/switch/{swtch}", method = RequestMethod.GET)
    public String getAll(ModelMap model, @PathVariable(value = "swtch", required = false) String swtch) {
        model.addAttribute("employees", super.getAll());
        model.addAttribute("swtch", swtch);
        return "employees";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String get(ModelMap model, @PathVariable("id") int id) {
        Employee employee = super.get(id).orElse(null);
        model.addAttribute("employee", employee);
        return "profile";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createOrUpdate(@ModelAttribute("employee") Employee employee) {
//        if (employee.isNew()) super.create()
        return "employees";
    }

    @RequestMapping(value = "/wsalary{id}", method = RequestMethod.GET)
    public String getWithSalaryEmailsPhones(ModelMap model, @PathVariable("id") int id) {
        Employee employee = super.getWithSalaryAndPhonesAndEmails(id).orElse(null);
        model.addAttribute("employee", employee);
        return "employee";
    }

    @RequestMapping(value = "/region/{regionId}", method = RequestMethod.GET)
    public String getAllByRegion(ModelMap model, @PathVariable("regionId") int regionId) {
        model.addAttribute("employees", super.getAllByRegion(regionId));
        return "employees";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String getAllByToken(ModelMap model, @RequestParam() String data, @RequestParam() String category) {

        List<Employee> employees = null;

        switch (category) {
            case "region":
                return "redirect:/regions/switch/emp";
            case "full_name":
                employees = super.getAllByFullName(data);
                break;
            case "login":
                    employees = List.of(super.findByLogin(data).orElse(null));
                break;
            case "address":
                employees = super.getAllByAddress(data);
                break;
        }

        model.addAttribute("employees", employees);

        return "employees";
    }

}
