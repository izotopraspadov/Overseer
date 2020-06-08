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
        return em.createNamedQuery("EmployeePayment:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    @Cacheable("employeePayments")
    public List<EmployeePayment> getAll(int first) {
        return em.createNamedQuery("EmployeePayment:all", EmployeePayment.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("employeePayments")
    public List<EmployeePayment> getAllByDate(LocalDate date, int first) {
        return em.createNamedQuery("EmployeePayment:allByDate", EmployeePayment.class)
                .setParameter("date", date)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("employeePayments")
    public List<EmployeePayment> getAllByEmployee(int employeeId, int first) {
        return em.createNamedQuery("EmployeePayment:allByEmployee", EmployeePayment.class)
                .setParameter("employeeId", employeeId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "employeePayments", allEntries = true)
    public void evictCache() {

    }
}
