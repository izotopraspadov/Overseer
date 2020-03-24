package edu.born.overseer.web.servlets;

import edu.born.overseer.web.AbstractPaymentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping(value = FinanceController.URL)
public class FinanceController extends AbstractPaymentController {

    static final String URL = "/finance";

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public String getAllByDate(ModelMap model, @RequestParam() String date) {
        LocalDate localDate = LocalDate.parse(date);

        model.addAttribute("orderedObjectPayments", super.getAllOrderedObjectPaymentsByDate(localDate));
        model.addAttribute("employeePayments", super.getAllEmployeePaymentsByDate(localDate));
        return "finance";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("orderedObjectPayments", super.getAllOrderedObjectPayments());
        model.addAttribute("employeePayments", super.getAllEmployeePayments());
        return "finance";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getAllByEmployee(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("payments", super.getAllByEmployee(id));
        return "employeePayments";
    }

    @RequestMapping(value = "/object/{id}", method = RequestMethod.GET)
    public String getAllByOrderedObject(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("payments", super.getAllByOrderedObject(id));
        return "objectPayments";
    }

}
