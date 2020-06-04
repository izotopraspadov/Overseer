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

    List<Order> getAll(int first);

    List<Order> getAllByCompany(int companyId, int first);

    List<Order> getAllByCashless(boolean cashless, int first);

    List<Order> getAllByGroup(int groupId, int first);

    List<Order> getAllByContractIsNeed(boolean contractIsNeed, int first);

    List<Order> getAllByContractExists(boolean contractExists, int first);

    List<Order> getAllByPlannedStartDate(LocalDate date, int first);

    List<Order> getAllByActualStartDate(LocalDate date, int first);

    List<Order> getAllByPlannedEndDate(LocalDate date, int first);

    List<Order> getAllByActualEndDate(LocalDate date, int first);

    List<Order> getAllBySum(BigDecimal currentSum, int first);

    List<Order> getAllByManager(int managerId, int first);

    List<Order> getAllByUnderway(boolean underway, int first);

    List<Order> getAllByExpectedPayment(BigDecimal expectedPayment, int first);

    List<Order> getAllByNumberOfLines(int numberOfLines, int first);

    // partial match using 'like'
    List<Order> getAllByPaymentFormat(String format, int first);

    // partial match using 'like'
    List<Order> getAllByTitle(String title, int first);

    // partial match using 'like'
    List<Order> getAllByOrderType(String orderType, int first);

}
