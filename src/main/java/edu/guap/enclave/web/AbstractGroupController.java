package edu.guap.enclave.web;

import edu.guap.enclave.model.Group;
import edu.guap.enclave.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractGroupController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GroupRepository groupRepository;

    public List<Group> getAll() {
        return groupRepository.getAll();
    }
}
