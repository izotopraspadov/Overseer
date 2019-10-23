package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.EmployeePayment;
import edu.guap.enclave.repository.EmployeePaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmployeePaymentRepositoryImpl implements EmployeePaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EmployeePayment> getAllByDate(LocalDate date) {
        return em.createNamedQuery(EmployeePayment.ALL_BY_DATE, EmployeePayment.class)
                .setParameter("date", date)
                .getResultList();
    }
}
