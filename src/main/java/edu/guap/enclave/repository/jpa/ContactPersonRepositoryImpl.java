package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.ContactPerson;
import edu.guap.enclave.repository.ContactPersonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ContactPersonRepositoryImpl implements ContactPersonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public ContactPerson save(ContactPerson person, int companyId) {

        if (!person.isNew() && get(person.getId(), companyId) == null) return null;

        person.setCompany(em.getReference(Company.class, companyId));

        if (person.isNew()) {
            em.persist(person);
            return person;
        } else {
            return em.merge(person);
        }
    }

    @Override
    public boolean delete(int id, int companyId) {
        return em.createNamedQuery(ContactPerson.DELETE)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .executeUpdate() != 0;
    }

    @Override
    public ContactPerson get(int id, int companyId) {
        return em.createNamedQuery(ContactPerson.GET, ContactPerson.class)
                .setParameter("id", id)
                .setParameter("companyId", companyId)
                .getSingleResult();
    }

    @Override
    public List<ContactPerson> getAll(int companyId) {
        return em.createNamedQuery(ContactPerson.ALL_SORTED, ContactPerson.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }
}
