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

import static edu.born.overseer.util.PageUtil.getFirstByPage;
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
        return em.createNamedQuery(Salary.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Salary getCurrentByEmployee(int employeeId) {
        return em.createNamedQuery(Salary.CURRENT_BY_EMPLYEE, Salary.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }

    @Override
    @Cacheable("salaries")
    public List<Salary> getAllByEmployee(Integer page, Integer employeeId) {
        return em.createNamedQuery(Salary.ALL_BY_EMPLOYEE, Salary.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("employeeId", employeeId)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "salaries", allEntries = true)
    public void evictCache() {

    }
}
