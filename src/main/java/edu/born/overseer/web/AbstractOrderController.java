package edu.born.overseer.web;

import edu.born.overseer.model.Order;
import edu.born.overseer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class AbstractOrderController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository orderRepository;

    public Order create(Order object, int companyId, int groupId, int managerId) {
        log.info("create object {}", object.getId());
        return orderRepository.save(object, companyId, groupId, managerId);
    }

    public Order update(Order object, int id, int companyId, int groupId, int managerId) {
        log.info("update object {}", id);
        return orderRepository.save(object, companyId, groupId, managerId);
    }

    public boolean delete(int id) {
        log.info("delete object {}", id);
        return orderRepository.delete(id);
    }

    public Optional<Order> get(int id) {
        log.info("get object {}", id);
        return orderRepository.get(id);
    }

    public Optional<Order> getWithPayments(int id) {
        log.info("get object {} with payments", id);
        return orderRepository.getWithPayments(id);
    }

    public Optional<Order> getWithTasks(int id) {
        log.info("get object {} with tasks", id);
        return orderRepository.getWithTasks(id);
    }

    public List<Order> getAll() {
        log.info("get all objects");
        return orderRepository.getAll();
    }

    public List<Order> getAllByTitle(String title) {
        log.info("get all objects by title {}", title);
        return orderRepository.getAllByTitle(title);
    }

    public List<Order> getAllByCompany(int companyId) {
        log.info("get all objects by company {}", companyId);
        return orderRepository.getAllByCompany(companyId);
    }

    public List<Order> getAllByCashless(boolean cashless) {
        log.info("get all objects by cashless {}", cashless);
        return orderRepository.getAllByCashless(cashless);
    }

    public List<Order> getAllByOrderType(String orderType) {
        log.info("get all objects by orderType {}", orderType);
        return orderRepository.getAllByOrderType(orderType);
    }

    public List<Order> getAllByGroup(int groupId) {
        log.info("get all objects by group {}", groupId);
        return orderRepository.getAllByGroup(groupId);
    }

    public List<Order> getAllByContractIsNeed(boolean contractIsNeed) {
        log.info("get all objects by contractIsNeed {}", contractIsNeed);
        return orderRepository.getAllByContractIsNeed(contractIsNeed);
    }

    public List<Order> getAllByContractExists(boolean contractExists) {
        log.info("get all objects by contractExists {}", contractExists);
        return orderRepository.getAllByContractExists(contractExists);
    }

    public List<Order> getAllByPlannedStartDate(LocalDate date) {
        return orderRepository.getAllByPlannedStartDate(date);
    }

    public List<Order> getAllByActualStartDate(LocalDate date) {
        return orderRepository.getAllByActualStartDate(date);
    }

    public List<Order> getAllByPlannedEndDate(LocalDate date) {
        return orderRepository.getAllByPlannedEndDate(date);
    }

    public List<Order> getAllByActualEndDate(LocalDate date) {
        return orderRepository.getAllByActualEndDate(date);
    }

    public List<Order> getAllBySum(BigDecimal currentSum) {
        log.info("get all objects by currentSum {}", currentSum);
        return orderRepository.getAllBySum(currentSum);
    }

    public List<Order> getAllByManager(int managerId) {
        log.info("get all objects by manager {}", managerId);
        return orderRepository.getAllByManager(managerId);
    }

    public List<Order> getAllByUnderway(boolean underway) {
        log.info("get all objects by underway {}", underway);
        return orderRepository.getAllByUnderway(underway);
    }

    public List<Order> getAllByExpectedPayment(BigDecimal expectedPayment) {
        log.info("get all objects by expectedPayment {}", expectedPayment);
        return orderRepository.getAllByExpectedPayment(expectedPayment);
    }

    public List<Order> getAllByPaymentOrder(String paymentOrder) {
        return orderRepository.getAllByPaymentFormat(paymentOrder);
    }

    public List<Order> getAllByNumberOfLines(int numberOfLines) {
        return orderRepository.getAllByNumberOfLines(numberOfLines);
    }
}
