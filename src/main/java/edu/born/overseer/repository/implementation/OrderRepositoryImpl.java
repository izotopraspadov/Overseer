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

import static edu.born.overseer.util.PageUtil.getPageLength;

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
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> getAll(int first) {
        return em.createNamedQuery("Order:all", Order.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByCompany(int companyId, int first) {
        return em.createNamedQuery("Order:allByCompany", Order.class)
                .setParameter("companyId", companyId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByCashless(boolean cashless, int first) {
        return em.createNamedQuery("Order:allByCashless", Order.class)
                .setParameter("cashless", cashless)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByGroup(int groupId, int first) {
        return em.createNamedQuery("Order:allByGroup", Order.class)
                .setParameter("groupId", groupId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractIsNeed(boolean contractIsNeed, int first) {
        return em.createNamedQuery("Order:allByContractIsNeed", Order.class)
                .setParameter("contractIsNeed", contractIsNeed)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByContractExists(boolean contractExists, int first) {
        return em.createNamedQuery("Order:allByContractExists", Order.class)
                .setParameter("contractExists", contractExists)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedStartDate(LocalDate date, int first) {
        return em.createNamedQuery("Order:allByPlannedStartDate", Order.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualStartDate(LocalDate date, int first) {
        return em.createNamedQuery("Order:allByActualStartDate", Order.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByPlannedEndDate(LocalDate date, int first) {
        return em.createNamedQuery("Order:allByPlannedEndDate", Order.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByActualEndDate(LocalDate date, int first) {
        return em.createNamedQuery("Order:allByActualEndDate", Order.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllBySum(BigDecimal currentSum, int first) {
        return em.createNamedQuery("Order:allBySum", Order.class)
                .setParameter("currentSum", currentSum)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByManager(int managerId, int first) {
        return em.createNamedQuery("Order:allByManager", Order.class)
                .setParameter("managerId", managerId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByUnderway(boolean underway, int first) {
        return em.createNamedQuery("Order:allByUnderway", Order.class)
                .setParameter("underway", underway)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByExpectedPayment(BigDecimal expectedPayment, int first) {
        return em.createNamedQuery("Order:allByExpectedPayment", Order.class)
                .setParameter("expectedPayment", expectedPayment)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByNumberOfLines(int numberOfLines, int first) {
        return em.createNamedQuery("Order:allByNumberOfLines", Order.class)
                .setParameter("numberOfLines", numberOfLines)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByPaymentFormat(String format, int first) {
        return em.createNamedQuery("Order:allByPaymentFormat", Order.class)
                .setParameter("paymentFormat", format)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByTitle(String title, int first) {
        return em.createNamedQuery("Order:allByTitle", Order.class)
                .setParameter("title", title)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Order> getAllByOrderType(String orderType, int first) {
        return em.createNamedQuery("Order:allByOrderType", Order.class)
                .setParameter("orderType", orderType)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

}
