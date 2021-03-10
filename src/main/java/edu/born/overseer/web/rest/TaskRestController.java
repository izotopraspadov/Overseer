package edu.born.overseer.web.rest;

import edu.born.overseer.model.Task;
import edu.born.overseer.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.TaskRestController.REST_URL;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class TaskRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/tasks";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskRepository taskRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Task create(@RequestBody Task task,
                       @PathVariable int orderId) {
        checkNew(task);
        int employeeId = task.getResponsible().getId();
        log.info("create task {} for order {} by employee {}", task, orderId, employeeId);
        return taskRepository.save(task, orderId, employeeId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Task task,
                       @PathVariable int id,
                       @PathVariable int orderId) {
        assureIdConsistent(task, id);
        int employeeId = task.getResponsible().getId();
        log.info("update task {} for order {} by employee {}", task, orderId, employeeId);
        taskRepository.save(task, orderId, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete task {}", id);
        taskRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = OK)
    public Task getById(@PathVariable int id) {
        log.info("get task {}", id);
        return taskRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = OK)
    public List<Task> getAllByOrder(@RequestParam(value = "page", required = false) Integer page,
                                    @PathVariable Integer orderId) {
        log.info("get all tasks by order {}", orderId);
        return taskRepository.getAllByOrder(page, orderId);
    }
}
