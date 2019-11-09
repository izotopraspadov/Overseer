package edu.guap.enclave.web;

import edu.guap.enclave.model.OrderedObject;
import edu.guap.enclave.repository.OrderedObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class AbstractOrderedObjectController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderedObjectRepository orderedObjectRepository;

    public OrderedObject create(OrderedObject object, int companyId, int groupId, int managerId) {
        log.info("create object {}", object.getId());
        return orderedObjectRepository.save(object, companyId, groupId, managerId);
    }

    public OrderedObject update(OrderedObject object, int id, int companyId, int groupId, int managerId) {
        log.info("update object {}", id);
        return orderedObjectRepository.save(object, companyId, groupId, managerId);
    }

    public boolean delete(int id) {
        log.info("delete object {}", id);
        return orderedObjectRepository.delete(id);
    }

    public Optional<OrderedObject> get(int id) {
        log.info("get object {}", id);
        return orderedObjectRepository.get(id);
    }

    public Optional<OrderedObject> getWithPayments(int id) {
        log.info("get object {} with payments", id);
        return orderedObjectRepository.getWithPayments(id);
    }

    public Optional<OrderedObject> getWithTasks(int id) {
        log.info("get object {} with tasks", id);
        return orderedObjectRepository.getWithTasks(id);
    }

    public List<OrderedObject> getAll() {
        log.info("get all objects");
        return orderedObjectRepository.getAll();
    }

    public List<OrderedObject> getAllByTitle(String title) {
        log.info("get all objects by title {}", title);
        return orderedObjectRepository.getAllByTitle(title);
    }

    public List<OrderedObject> getAllByCompany(int companyId) {
        log.info("get all objects by company {}", companyId);
        return orderedObjectRepository.getAllByCompany(companyId);
    }

    public List<OrderedObject> getAllByCashless(boolean cashless) {
        log.info("get all objects by cashless {}", cashless);
        return orderedObjectRepository.getAllByCashless(cashless);
    }

    public List<OrderedObject> getAllByOrderType(String orderType) {
        log.info("get all objects by orderType {}", orderType);
        return orderedObjectRepository.getAllByOrderType(orderType);
    }

    public List<OrderedObject> getAllByGroup(int groupId) {
        log.info("get all objects by group {}", groupId);
        return orderedObjectRepository.getAllByGroup(groupId);
    }

    public List<OrderedObject> getAllByContractIsNeed(boolean contractIsNeed) {
        log.info("get all objects by contractIsNeed {}", contractIsNeed);
        return orderedObjectRepository.getAllByContractIsNeed(contractIsNeed);
    }

    public List<OrderedObject> getAllByContractExists(boolean contractExists) {
        log.info("get all objects by contractExists {}", contractExists);
        return orderedObjectRepository.getAllByContractExists(contractExists);
    }

    public List<OrderedObject> getAllByPlannedStartDate(LocalDate date) {
        return orderedObjectRepository.getAllByPlannedStartDate(date);
    }

    public List<OrderedObject> getAllByActualStartDate(LocalDate date) {
        return orderedObjectRepository.getAllByActualStartDate(date);
    }

    public List<OrderedObject> getAllByPlannedEndDate(LocalDate date) {
        return orderedObjectRepository.getAllByPlannedEndDate(date);
    }

    public List<OrderedObject> getAllByActualEndDate(LocalDate date) {
        return orderedObjectRepository.getAllByActualEndDate(date);
    }

    public List<OrderedObject> getAllBySum(BigDecimal currentSum) {
        log.info("get all objects by currentSum {}", currentSum);
        return orderedObjectRepository.getAllBySum(currentSum);
    }

    public List<OrderedObject> getAllByManager(int managerId) {
        log.info("get all objects by manager {}", managerId);
        return orderedObjectRepository.getAllByManager(managerId);
    }

    public List<OrderedObject> getAllByUnderway(boolean underway) {
        log.info("get all objects by underway {}", underway);
        return orderedObjectRepository.getAllByUnderway(underway);
    }

    public List<OrderedObject> getAllByExpectedPayment(BigDecimal expectedPayment) {
        log.info("get all objects by expectedPayment {}", expectedPayment);
        return orderedObjectRepository.getAllByExpectedPayment(expectedPayment);
    }

    public List<OrderedObject> getAllByPaymentOrder(String paymentOrder) {
        return orderedObjectRepository.getAllByPaymentOrder(paymentOrder);
    }

    public List<OrderedObject> getAllByNumberOfLines(int numberOfLines) {
        return orderedObjectRepository.getAllByNumberOfLines(numberOfLines);
    }
}
