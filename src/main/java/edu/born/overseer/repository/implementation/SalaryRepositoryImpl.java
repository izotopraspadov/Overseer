package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.SalaryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class SalaryRepositoryImpl implements SalaryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "salaries", allEntries = true)
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
    @CacheEvict(value = "salaries", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery("Salary:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Salary getCurrentByEmployee(int employeeId) {
        return em.createNamedQuery("Salary:currentByEmployee", Salary.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }

    @Override
    @Cacheable("salaries")
    public List<Salary> getAllByEmployee(int employeeId, int first) {
        return em.createNamedQuery("Salary:allByEmployee", Salary.class)
                .setParameter("employeeId", employeeId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "salaries", allEntries = true)
    public void evictCache() {

    }
}
