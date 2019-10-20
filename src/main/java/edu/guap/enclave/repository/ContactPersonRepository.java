package edu.guap.enclave.repository;

import edu.guap.enclave.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository {

    ContactPerson save(ContactPerson person, int companyId);

    // false if not found
    boolean delete(int id);

    // null if not found
    ContactPerson get(int id);

    // null if not found
    ContactPerson getWithCompany(int id);

    List<ContactPerson> getAll();

    List<ContactPerson> getAllByCompany(int companyId);

}
