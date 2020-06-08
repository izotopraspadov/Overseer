package edu.born.overseer.repository;

import edu.born.overseer.model.OrderPayment;

import java.time.LocalDate;
import java.util.List;

public interface OrderPaymentRepository {

    OrderPayment save(OrderPayment payment, int orderId, int companyId, int ourCompanyId);

    // false if not found
    boolean delete(int id);

    List<OrderPayment> getAll(int first);

    List<OrderPayment> getAllByDate(LocalDate date, int first);

    List<OrderPayment> getAllByOrder(int orderId, int first);

    void evictCache();

}
