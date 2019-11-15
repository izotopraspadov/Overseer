package edu.guap.enclave.repository;

import edu.guap.enclave.model.OrderedObjectPayment;

import java.time.LocalDate;
import java.util.List;

public interface OrderedObjectPaymentRepository {

    List<OrderedObjectPayment> getAllByDate(LocalDate date);

    List<OrderedObjectPayment> getAllByOrderedObject(int orderedObjectId);

    List<OrderedObjectPayment> getAll();

}
