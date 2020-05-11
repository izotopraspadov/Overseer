package edu.born.overseer.repository;

import edu.born.overseer.model.EmployeePayment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeePaymentRepository {

    List<EmployeePayment> getAll();

    List<EmployeePayment> getAllByDate(LocalDate date);

    List<EmployeePayment> getAllByEmployee(int employeeId);

}
