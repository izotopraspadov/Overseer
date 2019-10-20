package edu.guap.enclave.repository;

import edu.guap.enclave.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee, int regionId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Employee get(int id);

    Employee getWithPayments(int id);

    Employee getWithSalary(int id);

    Employee getWithEmails(int id);

    Employee getWithPhones(int id);

    List<Employee> getAll();

    List<Employee> getAllByRegion(int regionId);

    Employee findByLogin(String login);

    Employee findByAddress(String address);

}
