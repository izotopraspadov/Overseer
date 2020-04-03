package edu.born.overseer.web;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import edu.born.overseer.repository.OrderPaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractPaymentController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Autowired
    private EmployeePaymentRepository employeePaymentRepository;

    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        return employeePaymentRepository.getAllByEmployee(employeeId);
    }

    public List<OrderPayment> getAllByOrderedObject(int orderedObjectId) {
        return orderPaymentRepository.getAllByOrder(orderedObjectId);
    }

    public List<OrderPayment> getAllOrderedObjectPaymentsByDate(LocalDate date) {
        log.info("get all object payments by date {}", date);
        return orderPaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllEmployeePaymentsByDate(LocalDate date) {
        log.info("get all employee payments by date {}", date);
        return employeePaymentRepository.getAllByDate(date);
    }

    public List<EmployeePayment> getAllEmployeePayments() {
        return employeePaymentRepository.getAll();
    }

    public List<OrderPayment> getAllOrderedObjectPayments() {
        return orderPaymentRepository.getAll();
    }
}
