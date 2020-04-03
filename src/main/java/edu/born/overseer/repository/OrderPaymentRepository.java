package edu.born.overseer.repository;

import edu.born.overseer.model.OrderPayment;

import java.time.LocalDate;
import java.util.List;

public interface OrderPaymentRepository {

    List<OrderPayment> getAllByDate(LocalDate date);

    List<OrderPayment> getAllByOrder(int orderId);

    List<OrderPayment> getAll();

}
