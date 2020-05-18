package edu.born.overseer.web.rest.time;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.repository.ActualTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = ActualTimeRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ActualTimeRestController {

    public static final String REST_URL = "/rest/orders/{orderId}/actualtime";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ActualTimeRepository actualTimeRepository;

    public ActualTimeRestController(ActualTimeRepository actualTimeRepository) {
        this.actualTimeRepository = actualTimeRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActualTime create(@RequestBody ActualTime actualTime, @PathVariable int orderId) {
        int employeeId = actualTime.getEmployee().getId();
        log.info("create actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        return actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody ActualTime actualTime, @PathVariable int id, @PathVariable int orderId) {
        int employeeId = actualTime.getEmployee().getId();
        log.info("update actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete actualTime {}", id);
        return actualTimeRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActualTime getById(@PathVariable int id) {
        log.info("get actualTime {}", id);
        return actualTimeRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ActualTime> getAllByOrder(int orderId) {
        log.info("get all actualTime by order {}", orderId);
        return actualTimeRepository.getAllByOrder(orderId);
    }

}
