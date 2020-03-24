package edu.born.overseer.repository;

import edu.born.overseer.model.OrderedObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderedObjectRepository {

    OrderedObject save(OrderedObject object, int companyId, int groupId, int managerId);

    // false if not found
    boolean delete(int id);

    Optional<OrderedObject> get(int id);

    Optional<OrderedObject> getWithPayments(int id);

    Optional<OrderedObject> getWithTasks(int id);

    List<OrderedObject> getAll();

    List<OrderedObject> getAllByTitle(String title);

    List<OrderedObject> getAllByCompany(int companyId);

    List<OrderedObject> getAllByCashless(boolean cashless);

    List<OrderedObject> getAllByOrderType(String orderType);

    List<OrderedObject> getAllByGroup(int groupId);

    List<OrderedObject> getAllByContractIsNeed(boolean contractIsNeed);

    List<OrderedObject> getAllByContractExists(boolean contractExists);

    List<OrderedObject> getAllByPlannedStartDate(LocalDate date);

    List<OrderedObject> getAllByActualStartDate(LocalDate date);

    List<OrderedObject> getAllByPlannedEndDate(LocalDate date);

    List<OrderedObject> getAllByActualEndDate(LocalDate date);

    List<OrderedObject> getAllBySum(BigDecimal currentSum);

    List<OrderedObject> getAllByManager(int managerId);

    List<OrderedObject> getAllByUnderway(boolean underway);

    List<OrderedObject> getAllByExpectedPayment(BigDecimal expectedPayment);

    List<OrderedObject> getAllByPaymentOrder(String paymentOrder);

    List<OrderedObject> getAllByNumberOfLines(int numberOfLines);

}
