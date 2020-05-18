package edu.born.overseer.web;

import edu.born.overseer.model.Group;
import edu.born.overseer.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractGroupController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GroupRepository groupRepository;

    public Group create(Group group) {
        log.info("create company {}", group);
        return groupRepository.save(group);
    }

    public Group update(Group group, int id) {
        log.info("update company {}", group);
        return groupRepository.save(group);
    }

    public boolean delete(int id) {
        log.info("delete group {}", id);
        return groupRepository.delete(id);
    }

    public Group getById(int id) {
        log.info("get group {}", id);
        return groupRepository.getById(id);
    }

    public List<Group> getAll() {
        log.info("get all groups");
        return groupRepository.getAll();
    }

}
