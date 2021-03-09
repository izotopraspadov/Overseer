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

    List<Order> getAll(Integer page,
                       Integer companyId,
                       Boolean cashless,
                       Integer groupId,
                       Boolean contractIsNeed,
                       Boolean contractExists,
                       LocalDate plannedStartDate,
                       LocalDate actualStartDate,
                       LocalDate plannedEndDate,
                       LocalDate actualEndDate,
                       BigDecimal currentSum,
                       Integer managerId,
                       Boolean underway,
                       BigDecimal expectedPayment,
                       Integer numberOfLines,
                       String format,
                       String title);

    void evictCache();

}
