package edu.born.overseer.repository;

import edu.born.overseer.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee, int regionId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Employee getById(int id);

    // null if not found
    Employee getByLogin(String login);

    List<Employee> getAll();

    List<Employee> getAllByRegion(int regionId);

    // partial match using 'like'
    List<Employee> getAllByAddress(String address);

    // partial match using 'like'
    List<Employee> getAllByFullName(String fullName);

}
