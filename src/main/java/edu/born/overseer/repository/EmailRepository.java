package edu.born.overseer.repository;

import edu.born.overseer.model.Email;
import edu.born.overseer.model.OwnerType;

import java.util.List;

public interface EmailRepository {

    List<Email> getAllBySpecificOwner(int ownerId, OwnerType owner);

}
