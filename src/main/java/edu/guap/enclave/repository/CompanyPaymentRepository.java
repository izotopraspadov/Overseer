package edu.guap.enclave.repository;

import edu.guap.enclave.model.OrderedObjectPayment;

import java.time.LocalDate;
import java.util.List;

public interface CompanyPaymentRepository {

    OrderedObjectPayment save(OrderedObjectPayment payment, int companyId, int ourCompanyId);

    // false if not found
    boolean delete(int id, int companyId, int ourCompanyId);

    // null if not found
    OrderedObjectPayment get(int id, int companyId);

    List<OrderedObjectPayment> getAllByDate(int companyId, LocalDate date);

}
