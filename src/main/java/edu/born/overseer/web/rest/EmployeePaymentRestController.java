package edu.born.overseer.web.rest;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;
import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.EmployeePaymentRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class EmployeePaymentRestController {

    public static final String REST_URL = "/rest/employees/{employeeId}/payments";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeePaymentRepository employeePaymentRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EmployeePayment create(@RequestBody EmployeePayment payment,
                                  @PathVariable int employeeId) {
        checkNew(payment);
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

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody EmployeePayment payment,
                       @PathVariable int id,
                       @PathVariable int employeeId) {
        assureIdConsistent(payment, id);
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

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<EmployeePayment> getAllByEmployee(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "date", required = false) LocalDate date,
                                                  @RequestParam(value = "employee_id", required = false) @PathVariable Integer employeeId) {
        log.info("get all employee payments by employee {}", employeeId);
        return employeePaymentRepository.getAll(page, date, employeeId);
    }
}