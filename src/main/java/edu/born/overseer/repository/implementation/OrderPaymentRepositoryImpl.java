package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.OrderPaymentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class OrderPaymentRepositoryImpl implements OrderPaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "orderPayments", allEntries = true)
    public OrderPayment save(OrderPayment payment, int orderId, int companyId, int ourCompanyId) {

        payment.setOrder(em.getReference(Order.class, orderId));
        payment.setCompany(em.getReference(Company.class, companyId));
        payment.setOurCompany(em.getReference(Company.class, ourCompanyId));

        if (payment.isNew()) {
            em.persist(payment);
            return payment;
        } else {
            return em.merge(payment);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "orderPayments", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery("OrderPayment:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Cacheable("orderPayments")
    public List<OrderPayment> getAll(int first) {
        return em.createNamedQuery("OrderPayment:all", OrderPayment.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("orderPayments")
    public List<OrderPayment> getAllByDate(LocalDate date, int first) {
        return em.createNamedQuery("OrderPayment:allByDate", OrderPayment.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("orderPayments")
    public List<OrderPayment> getAllByOrder(int orderId, int first) {
        return em.createNamedQuery("OrderPayment:allByOrder", OrderPayment.class)
                .setParameter("orderId", orderId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "orderPayments", allEntries = true)
    public void evictCache() {

    }
}
