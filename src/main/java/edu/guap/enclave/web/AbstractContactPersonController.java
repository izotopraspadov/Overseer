package edu.guap.enclave.web;

import edu.guap.enclave.model.ContactPerson;
import edu.guap.enclave.repository.ContactPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractContactPersonController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    public List<ContactPerson> getAll() {
        return contactPersonRepository.getAll();
    }
}
