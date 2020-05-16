package edu.born.overseer.repository;

import edu.born.overseer.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {

    Order save(Order order, int companyId, int groupId, int managerId, int orderTypeId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Order getById(int id);

    List<Order> getAll();

    List<Order> getAllByCompany(int companyId);

    List<Order> getAllByCashless(boolean cashless);

    List<Order> getAllByGroup(int groupId);

    List<Order> getAllByContractIsNeed(boolean contractIsNeed);

    List<Order> getAllByContractExists(boolean contractExists);

    List<Order> getAllByPlannedStartDate(LocalDate date);

    List<Order> getAllByActualStartDate(LocalDate date);

    List<Order> getAllByPlannedEndDate(LocalDate date);

    List<Order> getAllByActualEndDate(LocalDate date);

    List<Order> getAllBySum(BigDecimal currentSum);

    List<Order> getAllByManager(int managerId);

    List<Order> getAllByUnderway(boolean underway);

    List<Order> getAllByExpectedPayment(BigDecimal expectedPayment);

    List<Order> getAllByNumberOfLines(int numberOfLines);

    // partial match using 'like'
    List<Order> getAllByPaymentFormat(String format);

    // partial match using 'like'
    List<Order> getAllByTitle(String title);

    // partial match using 'like'
    List<Order> getAllByOrderType(String orderType);

}
