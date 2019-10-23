package edu.guap.enclave.repository;

import edu.guap.enclave.model.Group;

import java.util.List;

public interface GroupRepository {

    Group save(Group group);

    // false if not found
    boolean delete(int id);

    // null if not found
    Group get(int id);

    List<Group> getAll();

}
