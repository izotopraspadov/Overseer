package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.OrderedObjectPayment;
import edu.guap.enclave.repository.CompanyPaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CompanyPaymentRepositoryImpl implements CompanyPaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public OrderedObjectPayment save(OrderedObjectPayment payment, int companyId, int ourCompanyId) {
        if (!payment.isNew() && get(payment.getId(), companyId) == null) return null;

        payment.setCompany(em.getReference(Company.class, companyId));

        if (payment.isNew()) {
            em.persist(payment);
            return payment;
        } else {
            return em.merge(payment);
        }

    }

    @Override
    public boolean delete(int id, int companyId, int ourCompanyId) {
        return em.createNamedQuery(OrderedObjectPayment.DELETE)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .setParameter("ourCompanyId", ourCompanyId)
                .executeUpdate() != 0;
    }

    @Override
    public OrderedObjectPayment get(int id, int companyId) {
        return em.createNamedQuery(OrderedObjectPayment.GET, OrderedObjectPayment.class)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .getSingleResult();
    }

    @Override
    public List<OrderedObjectPayment> getAllByDate(int companyId, LocalDate date) {
        return em.createNamedQuery(OrderedObjectPayment.ALL_FILTERED, OrderedObjectPayment.class)
                .setParameter("companyId", companyId)
                .setParameter("date", date)
                .getResultList();
    }
}
