package edu.born.overseer.web.rest;

import edu.born.overseer.model.Group;
import edu.born.overseer.repository.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.GroupRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class GroupRestController {

    public static final String REST_URL = "/rest/groups";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GroupRepository groupRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Group create(@RequestBody Group group) {
        checkNew(group);
        log.info("create company {}", group);
        return groupRepository.save(group);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody Group group,
                       @PathVariable int id) {
        assureIdConsistent(group, id);
        log.info("update company {}", group);
        groupRepository.save(group);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete group {}", id);
        groupRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Group getById(@PathVariable int id) {
        log.info("get group {}", id);
        return groupRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Group> getAll(@RequestParam(value = "page", required = false) Integer page) {
        log.info("get all groups");
        return groupRepository.getAll(page);
    }
}