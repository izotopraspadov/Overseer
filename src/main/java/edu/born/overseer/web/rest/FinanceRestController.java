package edu.born.overseer.web.rest;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import edu.born.overseer.repository.OrderPaymentRepository;
import kotlin.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final OrderPaymentRepository orderPaymentRepository;
    public final EmployeePaymentRepository employeePaymentRepository;

    public FinanceRestController(OrderPaymentRepository orderPaymentRepository, EmployeePaymentRepository employeePaymentRepository) {
        this.orderPaymentRepository = orderPaymentRepository;
        this.employeePaymentRepository = employeePaymentRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Pair<List<EmployeePayment>, List<OrderPayment>> getAll() {
        return new Pair<>(employeePaymentRepository.getAll(), orderPaymentRepository.getAll());
    }

    @GetMapping(params = {"date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Pair<List<EmployeePayment>, List<OrderPayment>> getAllByDate(@RequestParam("date") LocalDate date) {
        return new Pair<>(employeePaymentRepository.getAllByDate(date), orderPaymentRepository.getAllByDate(date));
    }

}