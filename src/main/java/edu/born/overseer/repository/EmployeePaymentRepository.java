package edu.born.overseer.repository;

import edu.born.overseer.model.EmployeePayment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeePaymentRepository {

    EmployeePayment save(EmployeePayment payment, int employeeId, int companyCounterpartyId, int employeeCounterpartyId);

    // false if not found
    boolean delete(int id);

    List<EmployeePayment> getAll(int first);

    List<EmployeePayment> getAllByDate(LocalDate date, int first);

    List<EmployeePayment> getAllByEmployee(int employeeId, int first);

    void evictCache();

}
