package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;
import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class EmployeePaymentRepositoryImpl implements EmployeePaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "employeePayments", allEntries = true)
    public EmployeePayment save(EmployeePayment payment, int employeeId, int companyCounterpartyId, int employeeCounterpartyId) {

        payment.setEmployee(em.getReference(Employee.class, employeeId));

        if (payment.getCounterpartyType() == EMPLOYEE)
            payment.setEmployeeCounterparty(em.getReference(Employee.class, employeeCounterpartyId));
        else
            payment.setCompanyCounterparty(em.getReference(Company.class, companyCounterpartyId));

        if (payment.isNew()) {
            em.persist(payment);
            return payment;
        } else {
            return em.merge(payment);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "employeePayments", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(EmployeePayment.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Cacheable("employeePayments")
    public List<EmployeePayment> getAll(Integer page, LocalDate date, Integer employeeId) {
        return em.createNamedQuery(EmployeePayment.ALL, EmployeePayment.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("date", date)
                .setParameter("employeeId", employeeId)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "employeePayments", allEntries = true)
    public void evictCache() {

    }
}
