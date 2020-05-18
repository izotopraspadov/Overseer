package edu.born.overseer.web.rest.payment;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.model.OrderPayment;
import kotlin.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = FinanceRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FinanceRestController {

    public static final String REST_URL = "/rest/finances";

    private AbstractEmployeePaymentController employeePaymentController = new AbstractEmployeePaymentController() {
    };
    private AbstractOrderPaymentController orderPaymentController = new AbstractOrderPaymentController() {
    };

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Pair<List<EmployeePayment>, List<OrderPayment>> getAll() {
        return new Pair<>(employeePaymentController.getAll(), orderPaymentController.getAll());
    }

    @GetMapping(params = {"date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pair<List<EmployeePayment>, List<OrderPayment>> getAllByDate(@RequestParam("date") LocalDate date) {
        return new Pair<>(employeePaymentController.getAllByDate(date), orderPaymentController.getAllByDate(date));
    }

}
