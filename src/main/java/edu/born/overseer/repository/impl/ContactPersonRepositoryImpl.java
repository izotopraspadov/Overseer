package edu.born.overseer.repository.impl;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class ContactPersonRepositoryImpl implements ContactPersonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "persons", allEntries = true)
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
    @CacheEvict(value = "persons", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(ContactPerson.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ContactPerson getById(int id) {
        return em.find(ContactPerson.class, id);
    }

    @Override
    public ContactPerson getByIdWithCompany(int id) {
        return em.createNamedQuery(ContactPerson.BY_ID_WITH_COMPANY, ContactPerson.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    @Cacheable("persons")
    public List<ContactPerson> getAll(Integer page, Integer companyId) {
        return em.createNamedQuery(ContactPerson.BY_CONTACT_PERSON, ContactPerson.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("companyId", companyId)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "persons", allEntries = true)
    public void evictCache() {

    }
}
