package edu.born.overseer.repository;

import edu.born.overseer.model.Phone;
import edu.born.overseer.model.OwnerType;

import java.util.List;

public interface PhoneRepository {

    List<Phone> getAllBySpecificOwner(int ownerId, OwnerType owner);

}
