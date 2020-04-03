package edu.born.overseer.repository;

import edu.born.overseer.model.PlannedTime;

import java.util.List;

public interface PlannedTimeRepository {

    PlannedTime save(PlannedTime plannedTime, int orderId);

    // false if not found
    boolean delete(int id);

    List<PlannedTime> getAllByOrder(int orderId);

}
