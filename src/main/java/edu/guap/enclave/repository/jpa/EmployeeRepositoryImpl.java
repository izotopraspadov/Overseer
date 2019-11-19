package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Employee;
import edu.guap.enclave.model.Region;
import edu.guap.enclave.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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
        return em.createNamedQuery(Employee.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Optional<Employee> get(int id) {
        return em.createNamedQuery(Employee.GET, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }
    @Override
    public Optional<Employee> getWithPayments(int id) {
        return em.createNamedQuery(Employee.GET_WITH_PAYMENTS, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Employee> getWithSalary(int id) {
        return em.createNamedQuery(Employee.GET_WITH_SALARY, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Employee> getWithSalaryAndPhonesAndEmails(int id) {
        return em.createNamedQuery(Employee.GET_WITH_SALARY_AND_PHONES_AND_EMAILS, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Employee> getWithEmails(int id) {
        return em.createNamedQuery(Employee.GET_WITH_EMAILS, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Employee> getWithPhones(int id) {
        return em.createNamedQuery(Employee.GET_WITH_PHONES, Employee.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public List<Employee> getAll() {
        return em.createNamedQuery(Employee.ALL, Employee.class)
                .getResultList();
    }

    @Override
    public List<Employee> getAllByRegion(int regionId) {
        return em.createNamedQuery(Employee.ALL_BY_REGION, Employee.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }

    @Override
    public List<Employee> getAllByAddress(String address) {
        return em.createNamedQuery(Employee.ALL_BY_ADDRESS, Employee.class)
                .setParameter("address", "%" + address + "%")
                .getResultList();
    }

    @Override
    public List<Employee> getAllByFullName(String fullName) {
        return em.createNamedQuery(Employee.ALL_BY_FULL_NAME, Employee.class)
                .setParameter("fullName", "%" + fullName + "%")
                .getResultList();
    }

    @Override
    public Optional<Employee> findByLogin(String login) {
        return em.createNamedQuery(Employee.FIND_BY_LOGIN, Employee.class)
                .setParameter("login", "%" + login + "%")
                .getResultList()
                .stream()
                .findFirst();
    }

}
