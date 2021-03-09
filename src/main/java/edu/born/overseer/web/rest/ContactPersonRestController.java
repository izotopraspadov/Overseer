package edu.born.overseer.web.rest;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.born.overseer.util.ValidationUtil.assureIdConsistent;
import static edu.born.overseer.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ContactPersonRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactPersonRestController {

    public static final String REST_URL = "/rest/companies/{companyId}/persons";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ContactPersonRepository contactPersonRepository;

    public ContactPersonRestController(ContactPersonRepository contactPersonRepository) {
        this.contactPersonRepository = contactPersonRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactPerson create(@RequestBody ContactPerson person,
                                @PathVariable int companyId) {
        checkNew(person);
        log.info("create person {} for company {}", person, companyId);
        return contactPersonRepository.save(person, companyId);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody ContactPerson person,
                       @PathVariable("companyId") int companyId,
                       @PathVariable int id) {
        assureIdConsistent(person, id);
        log.info("create person {} for company {}", person, companyId);
        contactPersonRepository.save(person, companyId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete person {}", id);
        contactPersonRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactPerson getById(@PathVariable int id) {
        log.info("get person {}", id);
        return contactPersonRepository.getById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactPerson> getAllByCompany(@RequestParam(value = "page", required = false) Integer page,
                                               @PathVariable Integer companyId) {
        log.info("get all persons by company {}", companyId);
        return contactPersonRepository.getAll(page, companyId);
    }
}
