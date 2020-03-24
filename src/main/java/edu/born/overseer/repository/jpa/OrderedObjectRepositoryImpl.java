package edu.born.overseer.repository.jpa;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Group;
import edu.born.overseer.model.OrderedObject;
import edu.guap.enclave.model.*;
import edu.born.overseer.repository.OrderedObjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class OrderedObjectRepositoryImpl implements OrderedObjectRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public OrderedObject save(OrderedObject object, int companyId, int groupId, int managerId) {

        object.setCompany(em.getReference(Company.class, companyId));
        object.setGroup(em.getReference(Group.class, groupId));
        object.setManager(em.getReference(Employee.class, managerId));

        if (object.isNew()) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(OrderedObject.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Optional<OrderedObject> get(int id) {
        return em.createNamedQuery(OrderedObject.GET, OrderedObject.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<OrderedObject> getWithPayments(int id) {
        return em.createNamedQuery(OrderedObject.GET_WITH_PAYMENTS, OrderedObject.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<OrderedObject> getWithTasks(int id) {
        return em.createNamedQuery(OrderedObject.GET_WITH_TASKS, OrderedObject.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<OrderedObject> getAll() {
        return em.createNamedQuery(OrderedObject.ALL, OrderedObject.class)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByTitle(String title) {
        return em.createNamedQuery(OrderedObject.ALL_BY_TITLE, OrderedObject.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByCompany(int companyId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_COMPANY, OrderedObject.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByCashless(boolean cashless) {
        return em.createNamedQuery(OrderedObject.ALL_BY_CASHLESS, OrderedObject.class)
                .setParameter("cashless", cashless)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByOrderType(String orderType) {
        return em.createNamedQuery(OrderedObject.ALL_BY_ORDER_TYPE, OrderedObject.class)
                .setParameter("orderType", "%" + orderType + "%")
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByGroup(int groupId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_GROUP, OrderedObject.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByContractIsNeed(boolean contractIsNeed) {
        return em.createNamedQuery(OrderedObject.ALL_BY_CONTRACT_IS_NEED, OrderedObject.class)
                .setParameter("contractIsNeed", contractIsNeed)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByContractExists(boolean contractExists) {
        return em.createNamedQuery(OrderedObject.ALL_BY_CONTRACT_EXISTS, OrderedObject.class)
                .setParameter("contractExists", contractExists)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByPlannedStartDate(LocalDate date) {
        return em.createNamedQuery(OrderedObject.ALL_BY_PLANNED_START_DATE, OrderedObject.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByActualStartDate(LocalDate date) {
        return em.createNamedQuery(OrderedObject.ALL_BY_ACTUAL_START_DATE, OrderedObject.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByPlannedEndDate(LocalDate date) {
        return em.createNamedQuery(OrderedObject.ALL_BY_PLANNED_END_DATE, OrderedObject.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByActualEndDate(LocalDate date) {
        return em.createNamedQuery(OrderedObject.ALL_BY_ACTUAL_END_DATE, OrderedObject.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllBySum(BigDecimal currentSum) {
        return em.createNamedQuery(OrderedObject.ALL_BY_SUM, OrderedObject.class)
                .setParameter("currentSum", currentSum)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByManager(int managerId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_MANAGER, OrderedObject.class)
                .setParameter("managerId", managerId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByUnderway(boolean underway) {
        return em.createNamedQuery(OrderedObject.ALL_BY_UNDERWAY, OrderedObject.class)
                .setParameter("underway", underway)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByExpectedPayment(BigDecimal expectedPayment) {
        return em.createNamedQuery(OrderedObject.ALL_BY_EXPECTED_PAYMENT, OrderedObject.class)
                .setParameter("expectedPayment", expectedPayment)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByPaymentOrder(String paymentOrder) {
        return em.createNamedQuery(OrderedObject.ALL_BY_PAYMENT_ORDER, OrderedObject.class)
                .setParameter("paymentOrder", "%" + paymentOrder + "%")
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByNumberOfLines(int numberOfLines) {
        return em.createNamedQuery(OrderedObject.ALL_BY_NUMBER_OF_LINES, OrderedObject.class)
                .setParameter("numberOfLines", numberOfLines)
                .getResultList();
    }
}
