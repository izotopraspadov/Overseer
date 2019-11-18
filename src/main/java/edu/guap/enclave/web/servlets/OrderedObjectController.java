package edu.guap.enclave.web.servlets;

import edu.guap.enclave.model.OrderedObject;
import edu.guap.enclave.util.ValidationUtil;
import edu.guap.enclave.web.AbstractOrderedObjectController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = OrderedObjectController.URL)
public class OrderedObjectController extends AbstractOrderedObjectController {

    static final String URL = "/objects";

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("objects", super.getAll());
        return "objects";
    }

    @RequestMapping(value = "/group/{groupId}", method = RequestMethod.GET)
    public String getAllByGroup(ModelMap model, @PathVariable("groupId") int groupId) {
        model.addAttribute("objects", super.getAllByGroup(groupId));
        return "objects";
    }

    @RequestMapping(value = "/company/{companyId}", method = RequestMethod.GET)
    public String getAllByCompany(ModelMap model, @PathVariable("companyId") int companyId) {
        model.addAttribute("objects", super.getAllByCompany(companyId));
        return "objects";
    }

    @RequestMapping(value = "/manager/{managerId}", method = RequestMethod.GET)
    public String getAllByManager(ModelMap model, @PathVariable("managerId") int managerId) {
        model.addAttribute("objects", super.getAllByManager(managerId));
        return "objects";
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String getAllByToken(ModelMap model, @RequestParam() String data,
                                @RequestParam() String category,
                                @RequestParam(required = false) String date,
                                @RequestParam(required = false) String bool) {

        List<OrderedObject> objects = null;

        switch (category) {
            case "company":
                return "redirect:/companies/switch/obj";
            case "group":
                return "redirect:/groups";
            case "manager":
                return "redirect:/employees/switch/obj";
            case "title":
                objects = super.getAllByTitle(data);
                break;
            case "expected_payment":
                if (ValidationUtil.isNumeric(data))
                objects = super.getAllByExpectedPayment(new BigDecimal(data));
                break;
            case "order_type":
                objects = super.getAllByOrderType(data);
                break;
            case "contract_is_need":
                objects = super.getAllByContractIsNeed(Boolean.parseBoolean(bool));
                break;
            case "contract_exists":
                objects = super.getAllByContractExists(Boolean.parseBoolean(bool));
                break;
            case "planned_start_date":
                objects = super.getAllByPlannedStartDate(LocalDate.parse(date));
                break;
            case "actual_start_date":
                objects = super.getAllByActualStartDate(LocalDate.parse(date));
                break;
            case "planned_end_date":
                objects = super.getAllByPlannedEndDate(LocalDate.parse(date));
                break;
            case "actual_end_date":
                objects = super.getAllByActualEndDate(LocalDate.parse(date));
                break;
            case "sum":
                if (ValidationUtil.isNumeric(data))
                objects = super.getAllBySum(new BigDecimal(data));
                break;
            case "payment_order":
                objects = super.getAllByPaymentOrder(data);
                break;
            case "number_of_lines":
                if (ValidationUtil.isNumeric(data))
                objects = super.getAllByNumberOfLines(Integer.valueOf(data));
                break;
            case "underway":
                objects = super.getAllByUnderway(Boolean.parseBoolean(bool));
                break;
        }

        model.addAttribute("objects", objects);

        return "objects";
    }

}
