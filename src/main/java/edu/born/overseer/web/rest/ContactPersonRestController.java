package edu.born.overseer.web.rest;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;
import static edu.born.overseer.web.rest.ContactPersonRestController.REST_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = REST_URL, produces = APPLICATION_JSON_VALUE)
public class ContactPersonRestController {

    public static final String REST_URL = "/rest/companies/{companyId}/persons";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @ResponseStatus(value = CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ContactPerson create(@RequestBody ContactPerson person,
                                @PathVariable int companyId) {
        checkNew(person);
        log.info("create person {} for company {}", person, companyId);
        return contactPersonRepository.save(person, companyId);
    }

    @ResponseStatus(value = NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@RequestBody ContactPerson person,
                       @PathVariable("companyId") int companyId,
                       @PathVariable int id) {
        assureIdConsistent(person, id);
        log.info("create person {} for company {}", person, companyId);
        contactPersonRepository.save(person, companyId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete person {}", id);
        contactPersonRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ContactPerson getById(@PathVariable int id) {
        log.info("get person {}", id);
        return contactPersonRepository.getById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ContactPerson> getAllByCompany(@RequestParam(value = "page", required = false) Integer page,
                                               @PathVariable Integer companyId) {
        log.info("get all persons by company {}", companyId);
        return contactPersonRepository.getAll(page, companyId);
    }
}