package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Phone;
import edu.born.overseer.model.OwnerType;
import edu.born.overseer.repository.PhoneRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PhoneRepositoryImpl implements PhoneRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Phone> getAllBySpecificOwner(int ownerId, OwnerType owner) {

        switch (owner) {
            case EMPLOYEE:
                return em.createNamedQuery("Phone:allByEmployee", Phone.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            case CONTACT_PERSON:
                return em.createNamedQuery("Phone:allBContactPerson", Phone.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            default:
                return Collections.emptyList();
        }

    }

}
