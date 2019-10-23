package edu.guap.enclave.repository;

import edu.guap.enclave.model.ActualTime;

public interface ActualTimeRepository {

    ActualTime save(ActualTime actualTime, int orderedObjectId);

    // false if not found
    boolean delete(int id);

    // null if not found
    ActualTime getByOrderedObject(int orderedObjectId);

}
