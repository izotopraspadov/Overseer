package edu.born.overseer.repository;

import edu.born.overseer.model.EmployeePayment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeePaymentRepository {

    EmployeePayment save(EmployeePayment payment, int employeeId, int companyCounterpartyId, int employeeCounterpartyId);

    // false if not found
    boolean delete(int id);

    List<EmployeePayment> getAll(Integer page, LocalDate date, Integer employeeId);

    void evictCache();
}
