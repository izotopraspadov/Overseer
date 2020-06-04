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
import static edu.born.overseer.util.PageUtil.getFirstByPage;

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
                       @PathVariable int id,
                       @PathVariable int companyId) {
        assureIdConsistent(person, id);
        log.info("create person {} for company {}", person, companyId);
        contactPersonRepository.save(person, companyId);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable int id) {
        log.info("delete person {}", id);
        return contactPersonRepository.delete(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContactPerson getById(@PathVariable int id) {
        log.info("get person {}", id);
        return contactPersonRepository.getById(id);
    }

    @GetMapping(params = {"page"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactPerson> getAllByCompany(@PathVariable int companyId,
                                               @RequestParam(value = "page", required = false) Integer page) {
        log.info("get all persons by company {}", companyId);
        return contactPersonRepository.getAllByCompany(companyId, getFirstByPage(page));
    }

}
