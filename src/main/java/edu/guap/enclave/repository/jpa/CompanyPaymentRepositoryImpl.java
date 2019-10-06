package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.CompanyPayment;
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
    public CompanyPayment save(CompanyPayment payment, int companyId, int ourCompanyId) {
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
        return em.createNamedQuery(CompanyPayment.DELETE)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .setParameter("ourCompanyId", ourCompanyId)
                .executeUpdate() != 0;
    }

    @Override
    public CompanyPayment get(int id, int companyId) {
        return em.createNamedQuery(CompanyPayment.GET, CompanyPayment.class)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .getSingleResult();
    }

    @Override
    public List<CompanyPayment> getAllByDate(int companyId, LocalDate date) {
        return em.createNamedQuery(CompanyPayment.ALL_FILTERED, CompanyPayment.class)
                .setParameter("companyId", companyId)
                .setParameter("date", date)
                .getResultList();
    }
}
