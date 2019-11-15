package edu.guap.enclave.web;

import edu.guap.enclave.model.ActualTime;
import edu.guap.enclave.model.PlannedTime;
import edu.guap.enclave.repository.ActualTimeRepository;
import edu.guap.enclave.repository.PlannedTimeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTimeController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ActualTimeRepository actualTimeRepository;
    @Autowired
    private PlannedTimeRepository plannedTimeRepository;

    public List<ActualTime> getAllActualTimeByOrderedObject(int orderedObjectId) {
        return actualTimeRepository.getAllByOrderedObject(orderedObjectId);
    }

    public List<PlannedTime> getAllPlannedTimeByOrderedObject(int orderedObjectId) {
        return plannedTimeRepository.getAllByOrderedObject(orderedObjectId);
    }

}