package edu.born.overseer.repository.implementation;

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
        return em.createNamedQuery("Employee:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Employee getById(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    public Employee getByLogin(String login) {
        return em.createNamedQuery("Employee:byLogin", Employee.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAll(int first) {
        return em.createNamedQuery("Employee:all", Employee.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAllByRegion(int regionId, int first) {
        return em.createNamedQuery("Employee:allByRegion", Employee.class)
                .setParameter("regionId", regionId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAllByAddress(String address, int first) {
        return em.createNamedQuery("Employee:allByAddress", Employee.class)
                .setParameter("address", address)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAllByFullName(String fullName, int first) {
        return em.createNamedQuery("Employee:allByFullName", Employee.class)
                .setParameter("fullName", fullName)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "employees", allEntries = true)
    public void evictCache() {

    }
}
