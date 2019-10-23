package edu.guap.enclave.repository;

import edu.guap.enclave.model.Phone;
import edu.guap.enclave.model.TypeOwner;

import java.util.List;

public interface PhoneRepository {

    List<Phone> getAllBySpecificOwner(int ownerId, TypeOwner owner);

}
