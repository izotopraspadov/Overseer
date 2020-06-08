package edu.born.overseer.repository;

import edu.born.overseer.model.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Task task, int orderId, int employeeId);

    // false if not found
    boolean delete(int id);

    Task getById(int id);

    List<Task> getAllByOrder(int orderId, int first);

    void evictCache();

}
