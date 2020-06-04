package edu.born.overseer.repository;

import edu.born.overseer.model.Salary;

import java.util.List;

public interface SalaryRepository {

    Salary save(Salary salary, int employeeId);

    // false if not found
    boolean delete(int id);

    Salary getCurrentByEmployee(int employeeId);

    List<Salary> getAllByEmployee(int employeeId, int first);

}
