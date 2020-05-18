package edu.born.overseer.web;

import edu.born.overseer.model.PlannedTime;
import edu.born.overseer.repository.PlannedTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractPlannedTimeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlannedTimeRepository plannedTimeRepository;

    public PlannedTime create(PlannedTime plannedTime, int orderId, int employeeId) {
        log.info("create plannedTime {} for order {} by employee {}", plannedTime, orderId, employeeId);
        return plannedTimeRepository.save(plannedTime, orderId, employeeId);
    }

    public PlannedTime update(PlannedTime plannedTime, int id, int orderId, int employeeId) {
        log.info("update plannedTime {} for order {} by employee {}", plannedTime, orderId, employeeId);
        return plannedTimeRepository.save(plannedTime, orderId, employeeId);
    }

    public boolean delete(int id) {
        log.info("delete plannedTime {}", id);
        return plannedTimeRepository.delete(id);
    }

    public PlannedTime getById(int id) {
        log.info("get plannedTime {}", id);
        return plannedTimeRepository.getById(id);
    }

    public List<PlannedTime> getAllByOrder(int orderId) {
        log.info("get all plannedTime by order {}", orderId);
        return plannedTimeRepository.getAllByOrder(orderId);
    }

}
