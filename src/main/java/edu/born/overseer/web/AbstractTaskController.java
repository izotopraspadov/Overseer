package edu.born.overseer.web;

import edu.born.overseer.model.Task;
import edu.born.overseer.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTaskController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskRepository taskRepository;

    public Task create(Task task, int orderId, int employeeId) {
        log.info("create task {} for order {} by employee {}", task, orderId, employeeId);
        return taskRepository.save(task, orderId, employeeId);
    }

    public Task update(Task task, int id, int orderId, int employeeId) {
        log.info("update task {} for order {} by employee {}", task, orderId, employeeId);
        return taskRepository.save(task, orderId, employeeId);
    }

    public boolean delete(int id) {
        log.info("delete task {}", id);
        return taskRepository.delete(id);
    }

    public Task getById(int id) {
        log.info("get task {}", id);
        return taskRepository.getById(id);
    }

    public List<Task> getAllByOrder(int orderId) {
        log.info("get all tasks by order {}", orderId);
        return taskRepository.getAllByOrder(orderId);
    }

}
