package edu.born.overseer.web.rest;

import edu.born.overseer.model.PlannedTime;
import edu.born.overseer.repository.PlannedTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.PlannedTimeRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class PlannedTimeRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/plannedtime";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlannedTimeRepository plannedTimeRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public PlannedTime create(@RequestBody PlannedTime plannedTime,
                              @PathVariable int orderId) {
        checkNew(plannedTime);
        int employeeId = plannedTime.getEmployee().getId();
        log.info("create plannedTime {} for order {} by employee {}", plannedTime, orderId, employeeId);
        return plannedTimeRepository.save(plannedTime, orderId, employeeId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody PlannedTime plannedTime,
                       @PathVariable int id,
                       @PathVariable int orderId) {
        assureIdConsistent(plannedTime, id);
        int employeeId = plannedTime.getEmployee().getId();
        log.info("update plannedTime {} for order {} by employee {}", plannedTime, orderId, employeeId);
        plannedTimeRepository.save(plannedTime, orderId, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete plannedTime {}", id);
        plannedTimeRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public PlannedTime getById(@PathVariable int id) {
        log.info("get plannedTime {}", id);
        return plannedTimeRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<PlannedTime> getAllByOrder(@RequestParam(value = "page", required = false) Integer page,
                                           @PathVariable int orderId) {
        log.info("get all plannedTime by order {}", orderId);
        return plannedTimeRepository.getAll(page, orderId);
    }
}