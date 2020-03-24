package edu.born.overseer.repository;

import edu.born.overseer.model.ActualTime;

import java.util.List;

public interface ActualTimeRepository {

    ActualTime save(ActualTime actualTime, int orderedObjectId);

    // false if not found
    boolean delete(int id);

    List<ActualTime> getAllByOrderedObject(int orderedObjectId);

}
