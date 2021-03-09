package edu.born.overseer.repository;

import edu.born.overseer.model.PlannedTime;

import java.util.List;

public interface PlannedTimeRepository {

    PlannedTime save(PlannedTime plannedTime, int orderId, int employeeId);

    // false if not found
    boolean delete(int id);

    // null if not found
    PlannedTime getById(int id);

    List<PlannedTime> getAll(Integer page, Integer orderId);

    void evictCache();
}
