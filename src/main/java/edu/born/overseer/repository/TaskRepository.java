package edu.born.overseer.repository;

import edu.born.overseer.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getAllByOrderedObject(int orderedObjectId);

}
