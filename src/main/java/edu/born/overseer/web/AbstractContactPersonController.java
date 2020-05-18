package edu.born.overseer.web;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractContactPersonController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public ContactPerson create(ContactPerson person, int companyId) {
        log.info("create person {} for company {}", person, companyId);
        return contactPersonRepository.save(person, companyId);
    }

    public ContactPerson update(ContactPerson person, int id, int companyId) {
        log.info("create person {} for company {}", person, companyId);
        return contactPersonRepository.save(person, companyId);
    }

    public boolean delete(int id) {
        log.info("delete person {}", id);
        return contactPersonRepository.delete(id);
    }

    public ContactPerson getById(int id) {
        log.info("get person {}", id);
        return contactPersonRepository.getById(id);
    }

    public List<ContactPerson> getAll() {
        log.info("get all persons");
        return contactPersonRepository.getAll();
    }

    public List<ContactPerson> getAllByCompany(int companyId) {
        log.info("get all persons by company {}", companyId);
        return contactPersonRepository.getAllByCompany(companyId);
    }

}
