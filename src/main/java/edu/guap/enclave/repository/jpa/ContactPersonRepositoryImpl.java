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
    @Transactional
    public ContactPerson save(ContactPerson person, int companyId) {

        person.setCompany(em.getReference(Company.class, companyId));

        if (person.isNew()) {
            em.persist(person);
            return person;
        } else {
            return em.merge(person);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(ContactPerson.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ContactPerson get(int id) {
        return em.createNamedQuery(ContactPerson.GET, ContactPerson.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public ContactPerson getWithCompany(int id) {
        return em.createNamedQuery(ContactPerson.GET_WITH_COMPANY, ContactPerson.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ContactPerson> getAll() {
        return em.createNamedQuery(ContactPerson.ALL, ContactPerson.class)
                .getResultList();
    }

    @Override
    public List<ContactPerson> getAllByCompany(int companyId) {
        return em.createNamedQuery(ContactPerson.ALL_BY_COMPANY, ContactPerson.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }
}
