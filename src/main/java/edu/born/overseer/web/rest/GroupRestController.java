package edu.born.overseer.web.rest;

import edu.born.overseer.model.Group;
import edu.born.overseer.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = GroupRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupRestController {

    public static final String REST_URL = "/rest/groups";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final GroupRepository groupRepository;

    public GroupRestController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Group create(@RequestBody Group group) {
        log.info("create company {}", group);
        return groupRepository.save(group);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Group group, @PathVariable int id) {
        log.info("update company {}", group);
        groupRepository.save(group);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete group {}", id);
        return groupRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Group getById(@PathVariable int id) {
        log.info("get group {}", id);
        return groupRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAll() {
        log.info("get all groups");
        return groupRepository.getAll();
    }

}
