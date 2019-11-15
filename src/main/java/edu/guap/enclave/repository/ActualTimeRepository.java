package edu.guap.enclave.repository;

import edu.guap.enclave.model.ActualTime;

import java.util.List;

public interface ActualTimeRepository {

    ActualTime save(ActualTime actualTime, int orderedObjectId);

    // false if not found
    boolean delete(int id);

    List<ActualTime> getAllByOrderedObject(int orderedObjectId);

}
