package edu.born.overseer.repository;

import edu.born.overseer.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository {

    Order save(Order order, int companyId, int groupId, int managerId, int orderTypeId);

    // false if not found
    boolean delete(int id);

    Order getById(int id);

    Order getByIdWithPayments(int id);

    List<Order> getAll();

    List<Order> getAllByTitle(String title);

    List<Order> getAllByCompany(int companyId);

    List<Order> getAllByCashless(boolean cashless);

    List<Order> getAllByOrderType(String orderType);

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

    List<Order> getAllByPaymentFormat(String format);

    List<Order> getAllByNumberOfLines(int numberOfLines);

}
