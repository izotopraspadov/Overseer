package edu.guap.enclave.web;

import edu.guap.enclave.model.Task;
import edu.guap.enclave.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTaskController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllByOrderedObject(int orderedObjectId) {
        return taskRepository.getAllByOrderedObject(orderedObjectId);
    }
}
