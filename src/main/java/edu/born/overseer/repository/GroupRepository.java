package edu.born.overseer.repository;

import edu.born.overseer.model.Group;

import java.util.List;

public interface GroupRepository {

    Group save(Group group);

    // false if not found
    boolean delete(int id);

    // null if not found
    Group getById(int id);

    List<Group> getAll();

}
