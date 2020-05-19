package edu.born.overseer.web.rest;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.web.AbstractEmployeePaymentController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = EmployeePaymentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeePaymentRestController extends AbstractEmployeePaymentController {

    public static final String REST_URL = "/rest/employees/{employeeId}/payments";

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeePayment create(@RequestBody EmployeePayment payment, @PathVariable int employeeId) {
        return super.create(payment,
                employeeId,
                payment.getCompanyCounterparty().getId(),
                payment.getEmployeeCounterparty().getId());
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody EmployeePayment payment, @PathVariable int id, @PathVariable int employeeId) {
        super.update(payment,
                id,
                employeeId,
                payment.getCompanyCounterparty().getId(),
                payment.getEmployeeCounterparty().getId());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeePayment> getAllByEmployee(@PathVariable int employeeId) {
        return super.getAllByEmployee(employeeId);
    }

}
