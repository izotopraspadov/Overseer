package edu.born.overseer.service.impl;

import edu.born.overseer.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee, int regionId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Employee getById(int id);

    // null if not found
    Employee getByLogin(String login);

    List<Employee> getAll(Integer page, Integer regionId, String address, String fullName);
}
