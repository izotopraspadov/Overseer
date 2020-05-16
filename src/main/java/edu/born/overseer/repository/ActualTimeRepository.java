package edu.born.overseer.repository;

import edu.born.overseer.model.ActualTime;

import java.util.List;

public interface ActualTimeRepository {

    ActualTime save(ActualTime actualTime, int orderId, int employeeId);

    // false if not found
    boolean delete(int id);

    // null if not found
    ActualTime getById(int id);

    List<ActualTime> getAllByOrder(int orderId);

}
