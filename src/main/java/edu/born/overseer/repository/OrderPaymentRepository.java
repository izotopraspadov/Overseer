package edu.born.overseer.repository;

import edu.born.overseer.model.OrderPayment;

import java.time.LocalDate;
import java.util.List;

public interface OrderPaymentRepository {

    OrderPayment save(OrderPayment payment, int orderId, int companyId, int ourCompanyId);

    // false if not found
    boolean delete(int id);

    List<OrderPayment> getAll(Integer page, LocalDate date, Integer orderId);

    void evictCache();
}
