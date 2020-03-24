package edu.born.overseer.web;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.model.OrderedObjectPayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import edu.born.overseer.repository.OrderedObjectPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractPaymentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderedObjectPaymentRepository orderedObjectPaymentRepository;

    @Autowired
    private EmployeePaymentRepository employeePaymentRepository;

    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        return employeePaymentRepository.getAllByEmployee(employeeId);
    }

    public List<OrderedObjectPayment> getAllByOrderedObject(int orderedObjectId) {
        return orderedObjectPaymentRepository.getAllByOrderedObject(orderedObjectId);
    }

    public List<OrderedObjectPayment> getAllOrderedObjectPaymentsByDate(LocalDate date) {
        log.info("get all object payments by date {}", date);
        return orderedObjectPaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllEmployeePaymentsByDate(LocalDate date) {
        log.info("get all employee payments by date {}", date);
        return employeePaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllEmployeePayments() {
        return employeePaymentRepository.getAll();
    }

    public List<OrderedObjectPayment> getAllOrderedObjectPayments() {
        return orderedObjectPaymentRepository.getAll();
    }
}