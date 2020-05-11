package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Email;
import edu.born.overseer.model.OwnerType;
import edu.born.overseer.repository.EmailRepository;
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
    public List<Email> getAllBySpecificOwner(int ownerId, OwnerType owner) {

        switch (owner) {
            case EMPLOYEE:
                return em.createNamedQuery("Email:allByEmployee", Email.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            case CONTACT_PERSON:
                return em.createNamedQuery("Email:allBContactPerson", Email.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            default:
                return Collections.emptyList();
        }

    }

}
