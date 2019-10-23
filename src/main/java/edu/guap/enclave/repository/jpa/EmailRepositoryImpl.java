package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Email;
import edu.guap.enclave.model.TypeOwner;
import edu.guap.enclave.repository.EmailRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class EmailRepositoryImpl implements EmailRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Email> getAllBySpecificOwner(int ownerId, TypeOwner owner) {

        switch (owner) {
            case EMPLOYEE:
                return em.createNamedQuery(Email.ALL_BY_EMPLOYEE, Email.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            case CONTACT_PERSON:
                return em.createNamedQuery(Email.ALL_BY_CONTACT_PERSON, Email.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            default:
                return Collections.emptyList();
        }

    }
}
