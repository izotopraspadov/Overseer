package edu.born.overseer.web.rest;

import edu.born.overseer.model.Task;
import edu.born.overseer.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = TaskRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {

    public static final String REST_URL = "/rest/orders/{orderId}";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TaskRepository taskRepository;

    public TaskRestController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task create(@RequestBody Task task, @PathVariable int orderId) {
        int employeeId = task.getResponsible().getId();
        log.info("create task {} for order {} by employee {}", task, orderId, employeeId);
        return taskRepository.save(task, orderId, employeeId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Task task, @PathVariable int id, @PathVariable int orderId) {
        int employeeId = task.getResponsible().getId();
        log.info("update task {} for order {} by employee {}", task, orderId, employeeId);
        taskRepository.save(task, orderId, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete task {}", id);
        return taskRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Task getById(@PathVariable int id) {
        log.info("get task {}", id);
        return taskRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAllByOrder(@PathVariable int orderId) {
        log.info("get all tasks by order {}", orderId);
        return taskRepository.getAllByOrder(orderId);
    }

}