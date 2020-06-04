package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

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
        return em.createNamedQuery("ContactPerson:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ContactPerson getById(int id) {
        return em.find(ContactPerson.class, id);
    }

    @Override
    public ContactPerson getByIdWithCompany(int id) {
        return em.createNamedQuery("ContactPerson:byIdWithCompany", ContactPerson.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ContactPerson> getAll(int first) {
        return em.createNamedQuery("ContactPerson:all", ContactPerson.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<ContactPerson> getAllByCompany(int companyId, int first) {
        return em.createNamedQuery("ContactPerson:allByCompany", ContactPerson.class)
                .setParameter("companyId", companyId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

}
