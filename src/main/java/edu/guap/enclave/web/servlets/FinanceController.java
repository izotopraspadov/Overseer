package edu.guap.enclave.web.servlets;

import edu.guap.enclave.model.EmployeePayment;
import edu.guap.enclave.model.OrderedObjectPayment;
import edu.guap.enclave.model.abstract_entities.AbstractPaymentEntity;
import edu.guap.enclave.web.AbstractPaymentController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller
@RequestMapping(value = FinanceController.URL)
public class FinanceController extends AbstractPaymentController {

    static final String URL = "/finance";

    @RequestMapping(method = RequestMethod.GET)
    public String getAllByDate(ModelMap model, @RequestParam(required = false) LocalDate date) {
        List<OrderedObjectPayment> orderedObjectPayments = null;
        List<EmployeePayment> employeePayments = null;

        //if (date != null) {
            orderedObjectPayments = super.getAllOrderedObjectPaymentsByDate(LocalDate.of(2019, Month.SEPTEMBER, 29));
            employeePayments = super.getAllEmployeePaymentsByDate(LocalDate.of(2019, Month.OCTOBER, 4));
       // }
        model.addAttribute("orderedObjectPayments", orderedObjectPayments);
        model.addAttribute("employeePayments", employeePayments);
        return "finance";
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String getAllByEmployee(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("payments", super.getAllByEmployee(id));
        return "employeePayments";
    }

}
