package edu.guap.enclave.repository;

import edu.guap.enclave.model.EmployeePayment;

import java.time.LocalDate;
import java.util.List;

public interface EmployeePaymentRepository {

    List<EmployeePayment> getAllByDate(LocalDate date);

}
