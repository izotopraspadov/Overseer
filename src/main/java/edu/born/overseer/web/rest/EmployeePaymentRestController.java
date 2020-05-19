package edu.born.overseer.web.rest;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;

@RestController
@RequestMapping(value = EmployeePaymentRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeePaymentRestController {

    public static final String REST_URL = "/rest/employees/{employeeId}/payments";

    private final Logger log = LoggerFactory.getLogger(getClass());

    public final EmployeePaymentRepository employeePaymentRepository;

    public EmployeePaymentRestController(EmployeePaymentRepository employeePaymentRepository) {
        this.employeePaymentRepository = employeePaymentRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public EmployeePayment create(@RequestBody EmployeePayment payment, @PathVariable int employeeId) {
        if (payment.getCounterpartyType() == EMPLOYEE) {
            int employeeCounterpartyId = payment.getEmployeeCounterparty().getId();
            log.info("create employee payment {} for employee {} by employeeCounterparty {}", payment, employeeId, employeeCounterpartyId);
            return employeePaymentRepository.save(payment,
                    employeeId,
                    payment.getCompanyCounterparty().getId(),
                    payment.getEmployeeCounterparty().getId());
        } else {
            int companyCounterpartyId = payment.getEmployeeCounterparty().getId();
            log.info("create employee payment {} for employee {} by companyCounterparty {}", payment, employeeId, companyCounterpartyId);
            return employeePaymentRepository.save(payment,
                    employeeId,
                    payment.getCompanyCounterparty().getId(),
                    payment.getEmployeeCounterparty().getId());
        }
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody EmployeePayment payment, @PathVariable int id, @PathVariable int employeeId) {
        if (payment.getCounterpartyType() == EMPLOYEE) {
            int employeeCounterpartyId = payment.getEmployeeCounterparty().getId();
            log.info("update employee payment {} for employee {} by employeeCounterparty {}", payment, employeeId, employeeCounterpartyId);
            employeePaymentRepository.save(payment,
                    employeeId,
                    payment.getCompanyCounterparty().getId(),
                    payment.getEmployeeCounterparty().getId());
        } else {
            int companyCounterpartyId = payment.getEmployeeCounterparty().getId();
            log.info("update employee payment {} for employee {} by companyCounterparty {}", payment, employeeId, companyCounterpartyId);
            employeePaymentRepository.save(payment,
                    employeeId,
                    payment.getCompanyCounterparty().getId(),
                    payment.getEmployeeCounterparty().getId());
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmployeePayment> getAllByEmployee(@PathVariable int employeeId) {
        log.info("get all employee payments by employee {}", employeeId);
        return employeePaymentRepository.getAllByEmployee(employeeId);
    }

}
