package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Region;
import edu.born.overseer.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
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
    public boolean delete(int id) {
        return em.createNamedQuery("Employee:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Employee getById(int id) {
        return em.createNamedQuery("Employee:byId", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Employee getByLogin(String login) {
        return em.createNamedQuery("Employee:byLogin", Employee.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    public Employee getWithSalaryAndContacts(int id) {
        return em.createNamedQuery("Employee:byIdWithSalaryAndContacts", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Employee> getAll() {
        return em.createNamedQuery("Employee:all", Employee.class)
                .getResultList();
    }

    @Override
    public List<Employee> getAllByRegion(int regionId) {
        return em.createNamedQuery("Employee:allByRegion", Employee.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }

    @Override
    public List<Employee> getAllByAddress(String address) {
        return em.createNamedQuery("Employee:allByAddress", Employee.class)
                .setParameter("address", address)
                .getResultList();
    }

    @Override
    public List<Employee> getAllByFullName(String fullName) {
        return em.createNamedQuery("Employee:allByFullName", Employee.class)
                .setParameter("fullName", fullName)
                .getResultList();
    }

}
