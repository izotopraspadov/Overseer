package edu.guap.enclave.web;

import edu.guap.enclave.model.EmployeePayment;
import edu.guap.enclave.model.OrderedObjectPayment;
import edu.guap.enclave.repository.EmployeePaymentRepository;
import edu.guap.enclave.repository.OrderedObjectPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractPaymentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderedObjectPaymentRepository orderedObjectPaymentRepository;

    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        return employeePaymentRepository.getAllByEmployee(employeeId);
    }

    @Autowired
    private EmployeePaymentRepository employeePaymentRepository;

    public List<OrderedObjectPayment> getAllOrderedObjectPaymentsByDate(LocalDate date) {
        log.info("get all object payments by date {}", date);
        return orderedObjectPaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllEmployeePaymentsByDate(LocalDate date) {
        log.info("get all employee payments by date {}", date);
        return employeePaymentRepository.getAllByDate(date);
    }

}
