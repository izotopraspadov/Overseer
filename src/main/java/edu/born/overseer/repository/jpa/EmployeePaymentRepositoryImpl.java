package edu.born.overseer.repository.jpa;

import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
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

    @Override
    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        return em.createNamedQuery(EmployeePayment.ALL_BY_EMPLOYEE, EmployeePayment.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    @Override
    public List<EmployeePayment> getAll() {
        return em.createNamedQuery(EmployeePayment.ALL, EmployeePayment.class)
                .getResultList();
    }
}
