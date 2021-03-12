package edu.born.overseer.web.rest;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.repository.ActualTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.ActualTimeRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class ActualTimeRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/actualtime";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActualTimeRepository actualTimeRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ActualTime create(@RequestBody ActualTime actualTime,
                             @PathVariable int orderId) {
        checkNew(actualTime);
        int employeeId = actualTime.getEmployee().getId();
        log.info("create actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        return actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody ActualTime actualTime,
                       @PathVariable int id,
                       @PathVariable int orderId) {
        assureIdConsistent(actualTime, id);
        int employeeId = actualTime.getEmployee().getId();
        log.info("update actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete actualTime {}", id);
        actualTimeRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ActualTime getById(@PathVariable int id) {
        log.info("get actualTime {}", id);
        return actualTimeRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ActualTime> getAllByOrder(@RequestParam(value = "page", required = false) Integer page,
                                          @PathVariable Integer orderId) {
        log.info("get all actualTime by order {}", orderId);
        return actualTimeRepository.getAllByOrder(page, orderId);
    }
}