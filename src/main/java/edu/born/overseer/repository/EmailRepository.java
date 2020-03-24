package edu.born.overseer.repository;

import edu.born.overseer.model.Email;
import edu.born.overseer.model.TypeOwner;

import java.util.List;

public interface EmailRepository {

    List<Email> getAllBySpecificOwner(int ownerId, TypeOwner owner);

}
