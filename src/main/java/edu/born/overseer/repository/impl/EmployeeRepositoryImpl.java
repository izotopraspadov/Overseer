package edu.born.overseer.repository.impl;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Region;
import edu.born.overseer.repository.EmployeeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "employees", allEntries = true)
    public Employee save(Employee employee, int regionId) {

        employee.setRegion(em.getReference(Region.class, regionId));

        if (employee.isNew()) {
            em.persist(employee);
            return employee;
        } else {
            return em.merge(employee);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "employees", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(Employee.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Employee getById(int id) {
        return em.createNamedQuery(Employee.BY_ID, Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAll(Integer page, Integer regionId, String address, String fullName) {
        return em.createNamedQuery(Employee.ALL, Employee.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("regionId", regionId)
                .setParameter("address", Objects.toString(address, ""))
                .setParameter("fullName", Objects.toString(fullName, ""))
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public Employee getByLogin(String login) {
        return em.createNamedQuery(Employee.BY_LOGIN, Employee.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @CacheEvict(value = "employees", allEntries = true)
    public void evictCache() {

    }
}
