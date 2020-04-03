package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Phone;
import edu.born.overseer.model.TypeOwner;
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
    public List<Phone> getAllBySpecificOwner(int ownerId, TypeOwner owner) {

        switch (owner) {
            case EMPLOYEE:
                return em.createNamedQuery(Phone.ALL_BY_EMPLOYEE, Phone.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            case CONTACT_PERSON:
                return em.createNamedQuery(Phone.ALL_BY_CONTACT_PERSON, Phone.class)
                        .setParameter("ownerId", ownerId)
                        .getResultList();
            default:
                return Collections.emptyList();
        }

    }
}
