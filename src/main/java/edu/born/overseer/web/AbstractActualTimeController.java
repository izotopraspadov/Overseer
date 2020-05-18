package edu.born.overseer.web;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.repository.ActualTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractActualTimeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActualTimeRepository actualTimeRepository;

    public ActualTime create(ActualTime actualTime, int orderId, int employeeId) {
        log.info("create actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        return actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    public ActualTime update(ActualTime actualTime, int id, int orderId, int employeeId) {
        log.info("update actualTime {} for order {} by employee {}", actualTime, orderId, employeeId);
        return actualTimeRepository.save(actualTime, orderId, employeeId);
    }

    public boolean delete(int id) {
        log.info("delete actualTime {}", id);
        return actualTimeRepository.delete(id);
    }

    public ActualTime getById(int id) {
        log.info("get actualTime {}", id);
        return actualTimeRepository.getById(id);
    }

    public List<ActualTime> getAllByOrder(int orderId) {
        log.info("get all actualTime by order {}", orderId);
        return actualTimeRepository.getAllByOrder(orderId);
    }

}
