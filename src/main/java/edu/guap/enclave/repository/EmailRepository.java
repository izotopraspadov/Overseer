package edu.guap.enclave.repository;

import edu.guap.enclave.model.Email;
import edu.guap.enclave.model.TypeOwner;

import java.util.List;

public interface EmailRepository {

    List<Email> getAllBySpecificOwner(int ownerId, TypeOwner owner);

}
