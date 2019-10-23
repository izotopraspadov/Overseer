package edu.guap.enclave.repository;

import edu.guap.enclave.model.OrderedObject;

import java.math.BigDecimal;
import java.util.List;

public interface OrderedObjectRepository {

    OrderedObject save(OrderedObject object, int companyId, int groupId, int managerId);

    // false if not found
    boolean delete(int id);

    // null if not found
    OrderedObject get(int id);

    // null if not found
    OrderedObject getWithPayments(int id);

    // null if not found
    OrderedObject getWithTasks(int id);

    List<OrderedObject> getAll();

    List<OrderedObject> getAllByTitle(String title);

    List<OrderedObject> getAllByCompany(int companyId);

    List<OrderedObject> getAllByCashless(boolean cashless);

    List<OrderedObject> getAllByOrderType(String orderType);

    List<OrderedObject> getAllByGroup(int groupId);

    List<OrderedObject> getAllByContractExists(boolean contractExists);

    List<OrderedObject> getAllBySum(BigDecimal currentSum);

    List<OrderedObject> getAllByManager(int managerId);

    List<OrderedObject> getAllByUnderway(boolean underway);

}
