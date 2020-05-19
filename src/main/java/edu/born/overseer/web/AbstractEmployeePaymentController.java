package edu.born.overseer.web;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;

public abstract class AbstractEmployeePaymentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeePaymentRepository employeePaymentRepository;

    public EmployeePayment create(EmployeePayment payment, int employeeId, int companyCounterpartyId, int employeeCounterpartyId) {
        if (payment.getCounterpartyType() == EMPLOYEE)
            log.info("create employee payment {} for employee {} by employeeCounterparty {}", payment, employeeId, employeeCounterpartyId);
        else
            log.info("create employee payment {} for employee {} by companyCounterparty {}", payment, employeeId, companyCounterpartyId);

        return employeePaymentRepository.save(payment, employeeId, companyCounterpartyId, employeeCounterpartyId);
    }

    public EmployeePayment update(EmployeePayment payment, int id, int employeeId, int companyCounterpartyId, int employeeCounterpartyId) {
        if (payment.getCounterpartyType() == EMPLOYEE)
            log.info("update employee payment {} for employee {} by employeeCounterparty {}", payment, employeeId, employeeCounterpartyId);
        else
            log.info("update employee payment {} for employee {} by companyCounterparty {}", payment, employeeId, companyCounterpartyId);

        return employeePaymentRepository.save(payment, employeeId, companyCounterpartyId, employeeCounterpartyId);
    }

    public List<EmployeePayment> getAll() {
        log.info("get all employee payments");
        return employeePaymentRepository.getAll();
    }

    public List<EmployeePayment> getAllByDate(LocalDate date) {
        log.info("get all employee payments by date {}", date);
        return employeePaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        log.info("get all employee payments by employee {}", employeeId);
        return employeePaymentRepository.getAllByEmployee(employeeId);
    }

}