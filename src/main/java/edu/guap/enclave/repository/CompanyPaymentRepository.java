package edu.guap.enclave.repository;

import edu.guap.enclave.model.CompanyPayment;

import java.time.LocalDate;
import java.util.List;

public interface CompanyPaymentRepository {

    CompanyPayment save(CompanyPayment payment, int companyId, int ourCompanyId);

    // false if not found
    boolean delete(int id, int companyId, int ourCompanyId);

    // null if not found
    CompanyPayment get(int id, int companyId);

    List<CompanyPayment> getAllByDate(int companyId, LocalDate date);

}
