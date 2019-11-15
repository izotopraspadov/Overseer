package edu.guap.enclave.repository;

import edu.guap.enclave.model.PlannedTime;

import java.util.List;
import java.util.Optional;

public interface PlannedTimeRepository {

    PlannedTime save(PlannedTime plannedTime, int orderedObjectId);

    // false if not found
    boolean delete(int id);

    List<PlannedTime> getAllByOrderedObject(int orderedObjectId);

}
