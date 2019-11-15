package edu.guap.enclave.repository;

import edu.guap.enclave.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getAllByOrderedObject(int orderedObjectId);

}
