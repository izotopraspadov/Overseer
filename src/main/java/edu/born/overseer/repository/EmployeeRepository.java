package edu.born.overseer.repository;

import edu.born.overseer.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee, int regionId);

    // false if not found
    boolean delete(int id);

    Optional<Employee> get(int id);

    Optional<Employee> getWithPayments(int id);

    Optional<Employee> getWithSalary(int id);

    Optional<Employee> getWithSalaryAndPhonesAndEmails(int id);

    Optional<Employee> getWithEmails(int id);

    Optional<Employee> getWithPhones(int id);

    List<Employee> getAll();

    List<Employee> getAllByRegion(int regionId);

    List<Employee> getAllByAddress(String address);

    List<Employee> getAllByFullName(String fullName);

    Optional<Employee> findByLogin(String login);

}
