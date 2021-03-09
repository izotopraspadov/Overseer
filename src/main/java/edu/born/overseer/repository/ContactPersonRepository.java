package edu.born.overseer.repository;

import edu.born.overseer.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository {

    ContactPerson save(ContactPerson person, int companyId);

    // false if not found
    boolean delete(int id);

    // null if not found
    ContactPerson getById(int id);

    // null if not found
    ContactPerson getByIdWithCompany(int id);

    List<ContactPerson> getAll(Integer page, Integer companyId);

    void evictCache();
}
