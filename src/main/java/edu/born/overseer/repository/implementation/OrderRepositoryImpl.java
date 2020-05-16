package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.*;
import edu.born.overseer.repository.OrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order, int companyId, int groupId, int managerId, int orderTypeId) {

        order.setCompany(em.getReference(Company.class, companyId));
        order.setGroup(em.getReference(Group.class, groupId));
        order.setManager(em.getReference(Employee.class, managerId));
        order.setOrderType(em.getReference(OrderType.class, orderTypeId));

        if (order.isNew()) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery("Order:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Order getById(int id) {
        return em.createNamedQuery("Order:byId", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery("Order:all", Order.class)
                .getResultList();
    }

    @Override
    public List<Order> getAllByCompany(int companyId) {
        return em.createNamedQuery("Order:allByCompany", Order.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByCashless(boolean cashless) {
        return em.createNamedQuery("Order:allByCashless", Order.class)
                .setParameter("cashless", cashless)
                .getResultList();
    }

    @Override
    public List<Order> getAllByGroup(int groupId) {
        return em.createNamedQuery("Order:allByGroup", Order.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractIsNeed(boolean contractIsNeed) {
        return em.createNamedQuery("Order:allByContractIsNeed", Order.class)
                .setParameter("contractIsNeed", contractIsNeed)
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractExists(boolean contractExists) {
        return em.createNamedQuery("Order:allByContractExists", Order.class)
                .setParameter("contractExists", contractExists)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedStartDate(LocalDate date) {
        return em.createNamedQuery("Order:allByPlannedStartDate", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualStartDate(LocalDate date) {
        return em.createNamedQuery("Order:allByActualStartDate", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedEndDate(LocalDate date) {
        return em.createNamedQuery("Order:allByPlannedEndDate", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualEndDate(LocalDate date) {
        return em.createNamedQuery("Order:allByActualEndDate", Order.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Order> getAllBySum(BigDecimal currentSum) {
        return em.createNamedQuery("Order:allBySum", Order.class)
                .setParameter("currentSum", currentSum)
                .getResultList();
    }

    @Override
    public List<Order> getAllByManager(int managerId) {
        return em.createNamedQuery("Order:allByManager", Order.class)
                .setParameter("managerId", managerId)
                .getResultList();
    }

    @Override
    public List<Order> getAllByUnderway(boolean underway) {
        return em.createNamedQuery("Order:allByUnderway", Order.class)
                .setParameter("underway", underway)
                .getResultList();
    }

    @Override
    public List<Order> getAllByExpectedPayment(BigDecimal expectedPayment) {
        return em.createNamedQuery("Order:allByExpectedPayment", Order.class)
                .setParameter("expectedPayment", expectedPayment)
                .getResultList();
    }

    @Override
    public List<Order> getAllByNumberOfLines(int numberOfLines) {
        return em.createNamedQuery("Order:allByNumberOfLines", Order.class)
                .setParameter("numberOfLines", numberOfLines)
                .getResultList();
    }

    @Override
    public List<Order> getAllByPaymentFormat(String format) {
        return em.createNamedQuery("Order:allByPaymentFormat", Order.class)
                .setParameter("paymentFormat", format)
                .getResultList();
    }

    @Override
    public List<Order> getAllByTitle(String title) {
        return em.createNamedQuery("Order:allByTitle", Order.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Order> getAllByOrderType(String orderType) {
        return em.createNamedQuery("Order:allByOrderType", Order.class)
                .setParameter("orderType", orderType)
                .getResultList();
    }

}
