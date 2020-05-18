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

    public Order create(Order order, int companyId, int groupId, int managerId, int orderTypeId) {
        log.info("create order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        return orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    public Order update(Order order, int id, int companyId, int groupId, int managerId, int orderTypeId) {
        log.info("create order {} by company {} by group {} by manager {} by orderType {}", order, companyId, groupId, managerId, orderTypeId);
        return orderRepository.save(order, companyId, groupId, managerId, orderTypeId);
    }

    public boolean delete(int id) {
        log.info("delete order {}", id);
        return orderRepository.delete(id);
    }

    public Order getById(int id) {
        log.info("get order {}", id);
        return orderRepository.getById(id);
    }

    public List<Order> getAll() {
        log.info("get all orders");
        return orderRepository.getAll();
    }

    public List<Order> getAllByCompany(int companyId) {
        log.info("get all orders by company {}", companyId);
        return orderRepository.getAllByCompany(companyId);
    }

    public List<Order> getAllByCashless(boolean cashless) {
        log.info("get all orders by cashless {}", cashless);
        return orderRepository.getAllByCashless(cashless);
    }

    public List<Order> getAllByGroup(int groupId) {
        log.info("get all orders by group {}", groupId);
        return orderRepository.getAllByGroup(groupId);
    }

    public List<Order> getAllByContractIsNeed(boolean contractIsNeed) {
        log.info("get all orders by contractIsNeed {}", contractIsNeed);
        return orderRepository.getAllByContractIsNeed(contractIsNeed);
    }

    public List<Order> getAllByContractExists(boolean contractExists) {
        log.info("get all orders by contractExists {}", contractExists);
        return orderRepository.getAllByContractExists(contractExists);
    }

    public List<Order> getAllByPlannedStartDate(LocalDate date) {
        log.info("get all orders by plannedStartDate {}", date);
        return orderRepository.getAllByPlannedStartDate(date);
    }

    public List<Order> getAllByActualStartDate(LocalDate date) {
        log.info("get all orders by actualStartDate {}", date);
        return orderRepository.getAllByActualStartDate(date);
    }

    public List<Order> getAllByPlannedEndDate(LocalDate date) {
        log.info("get all orders by plannedEndDate {}", date);
        return orderRepository.getAllByPlannedEndDate(date);
    }

    public List<Order> getAllByActualEndDate(LocalDate date) {
        log.info("get all orders by actualEndDate {}", date);
        return orderRepository.getAllByActualEndDate(date);
    }

    public List<Order> getAllBySum(BigDecimal currentSum) {
        log.info("get all orders by currentSum {}", currentSum);
        return orderRepository.getAllBySum(currentSum);
    }

    public List<Order> getAllByManager(int managerId) {
        log.info("get all orders by managerId {}", managerId);
        return orderRepository.getAllByManager(managerId);
    }

    public List<Order> getAllByUnderway(boolean underway) {
        log.info("get all orders by underway {}", underway);
        return orderRepository.getAllByUnderway(underway);
    }

    public List<Order> getAllByExpectedPayment(BigDecimal expectedPayment) {
        log.info("get all orders by expectedPayment {}", expectedPayment);
        return orderRepository.getAllByExpectedPayment(expectedPayment);
    }

    public List<Order> getAllByNumberOfLines(int numberOfLines) {
        log.info("get all orders by numberOfLines {}", numberOfLines);
        return orderRepository.getAllByNumberOfLines(numberOfLines);
    }

    public List<Order> getAllByPaymentFormat(String format) {
        log.info("get all orders by PaymentFormat {}", format);
        return orderRepository.getAllByPaymentFormat(format);
    }

    public List<Order> getAllByTitle(String title) {
        log.info("get all orders by title {}", title);
        return orderRepository.getAllByTitle(title);
    }

    public List<Order> getAllByOrderType(String orderType) {
        log.info("get all orders by orderType {}", orderType);
        return orderRepository.getAllByOrderType(orderType);
    }

}
