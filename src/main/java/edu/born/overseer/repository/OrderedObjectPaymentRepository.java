package edu.born.overseer.repository;

import edu.born.overseer.model.OrderedObjectPayment;

import java.time.LocalDate;
import java.util.List;

public interface OrderedObjectPaymentRepository {

    List<OrderedObjectPayment> getAllByDate(LocalDate date);

    List<OrderedObjectPayment> getAllByOrderedObject(int orderedObjectId);

    List<OrderedObjectPayment> getAll();

}
