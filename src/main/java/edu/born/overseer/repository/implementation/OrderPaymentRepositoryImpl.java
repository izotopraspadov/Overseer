package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.OrderPayment;
import edu.born.overseer.repository.OrderPaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderPaymentRepositoryImpl implements OrderPaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
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
    public List<OrderPayment> getAll() {
        return em.createNamedQuery("OrderPayment:all", OrderPayment.class)
                .getResultList();
    }

    @Override
    public List<OrderPayment> getAllByDate(LocalDate date) {
        return em.createNamedQuery("OrderPayment:allByDate", OrderPayment.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderPayment> getAllByOrder(int orderId) {
        return em.createNamedQuery("OrderPayment:allByOrder", OrderPayment.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

}
