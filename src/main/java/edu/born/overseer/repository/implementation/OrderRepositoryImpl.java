package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Group;
import edu.born.overseer.model.Order;
import edu.born.overseer.repository.OrderRepository;
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
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order object, int companyId, int groupId, int managerId) {

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
        return em.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Optional<Order> get(int id) {
        return em.createNamedQuery(Order.BY_ID, Order.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Order> getWithPayments(int id) {
        return em.createNamedQuery(Order.BY_ID_WITH_PAYMENTS, Order.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Order> getWithTasks(int id) {
        return em.createNamedQuery(Order.BY_ID_WITH_TASKS, Order.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.ALL, Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getAllByTitle(String title) {
        return em.createNamedQuery(Order.ALL_BY_TITLE, Order.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<Order> getAllByCompany(int companyId) {
        return em.createNamedQuery(Order.ALL_BY_COMPANY, Order.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByCashless(boolean cashless) {
        return em.createNamedQuery(Order.ALL_BY_CASHLESS, Order.class)
                .setParameter("cashless", cashless)
                .getResultList();
    }

    @Override
    public List<Order> getAllByOrderType(String orderType) {
        return em.createNamedQuery(Order.ALL_BY_ORDER_TYPE, Order.class)
                .setParameter("orderType", "%" + orderType + "%")
                .getResultList();
    }

    @Override
    public List<Order> getAllByGroup(int groupId) {
        return em.createNamedQuery(Order.ALL_BY_GROUP, Order.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractIsNeed(boolean contractIsNeed) {
        return em.createNamedQuery(Order.ALL_BY_CONTRACT_IS_NEED, Order.class)
                .setParameter("contractIsNeed", contractIsNeed)
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractExists(boolean contractExists) {
        return em.createNamedQuery(Order.ALL_BY_CONTRACT_EXISTS, Order.class)
                .setParameter("contractExists", contractExists)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedStartDate(LocalDate date) {
        return em.createNamedQuery(Order.ALL_BY_PLANNED_START_DATE, Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualStartDate(LocalDate date) {
        return em.createNamedQuery(Order.ALL_BY_ACTUAL_START_DATE, Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedEndDate(LocalDate date) {
        return em.createNamedQuery(Order.ALL_BY_PLANNED_END_DATE, Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualEndDate(LocalDate date) {
        return em.createNamedQuery(Order.ALL_BY_ACTUAL_END_DATE, Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllBySum(BigDecimal currentSum) {
        return em.createNamedQuery(Order.ALL_BY_SUM, Order.class)
                .setParameter("currentSum", currentSum)
                .getResultList();
    }

    @Override
    public List<Order> getAllByManager(int managerId) {
        return em.createNamedQuery(Order.ALL_BY_MANAGER, Order.class)
                .setParameter("managerId", managerId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByUnderway(boolean underway) {
        return em.createNamedQuery(Order.ALL_BY_UNDERWAY, Order.class)
                .setParameter("underway", underway)
                .getResultList();
    }

    @Override
    public List<Order> getAllByExpectedPayment(BigDecimal expectedPayment) {
        return em.createNamedQuery(Order.ALL_BY_EXPECTED_PAYMENT, Order.class)
                .setParameter("expectedPayment", expectedPayment)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPaymentFormat(String format) {
        return em.createNamedQuery(Order.ALL_BY_PAYMENT_FORMAT, Order.class)
                .setParameter("paymentFormat", "%" + format + "%")
                .getResultList();
    }

    @Override
    public List<Order> getAllByNumberOfLines(int numberOfLines) {
        return em.createNamedQuery(Order.ALL_BY_NUMBER_OF_LINES, Order.class)
                .setParameter("numberOfLines", numberOfLines)
                .getResultList();
    }
}
