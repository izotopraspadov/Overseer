package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.EmployeePayment;
import edu.born.overseer.repository.EmployeePaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static edu.born.overseer.model.CounterpartyType.EMPLOYEE;

@Repository
@Transactional(readOnly = true)
public class EmployeePaymentRepositoryImpl implements EmployeePaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
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
    public List<EmployeePayment> getAll() {
        return em.createNamedQuery("EmployeePayment:all", EmployeePayment.class)
                .getResultList();
    }

    @Override
    public List<EmployeePayment> getAllByDate(LocalDate date) {
        return em.createNamedQuery("EmployeePayment:allByDate", EmployeePayment.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<EmployeePayment> getAllByEmployee(int employeeId) {
        return em.createNamedQuery("EmployeePayment:allByEmployee", EmployeePayment.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

}
