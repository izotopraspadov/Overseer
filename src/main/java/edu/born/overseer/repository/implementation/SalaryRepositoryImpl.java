package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SalaryRepositoryImpl implements SalaryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Salary save(Salary salary, int employeeId) {

        salary.setEmployee(em.getReference(Employee.class, employeeId));

        if (salary.isNew()) {
            em.persist(salary);
            return salary;
        } else {
            return em.merge(salary);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery("Salary:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Salary getCurrent() {
        return em.createNamedQuery("Salary:current", Salary.class)
                .getSingleResult();
    }

    @Override
    public List<Salary> getAllByEmployee(int employeeId) {
        return em.createNamedQuery("Salary:allByEmployee", Salary.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

}
