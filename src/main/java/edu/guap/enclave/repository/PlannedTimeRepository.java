package edu.guap.enclave.repository;

import edu.guap.enclave.model.PlannedTime;

public interface PlannedTimeRepository {

    PlannedTime save(PlannedTime plannedTime, int orderedObjectId);

    // false if not found
    boolean delete(int id);

    // null if not found
    PlannedTime getByOrderedObject(int orderedObjectId);

}
