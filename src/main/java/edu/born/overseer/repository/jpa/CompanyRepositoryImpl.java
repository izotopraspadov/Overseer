package edu.born.overseer.repository.jpa;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Reliability;
import edu.born.overseer.model.TypeCompany;
import edu.born.overseer.repository.CompanyRepository;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Company save(Company company) {
        if (company.isNew()) {
            em.persist(company);
            return company;
        } else {
            return em.merge(company);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Company.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Company getById(int id) {
        return em.createNamedQuery(Company.GET, Company.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Company> getAllByItb(String itn) {
        return em.createNamedQuery(Company.FIND_BY_ITN, Company.class)
                .setParameter("itn", "%" + itn + "%")
                .getResultList();
    }

    @Override
    public Company getByContactPersonId(int contactPersonId) {
        return em.createNamedQuery(Company.ALL_BY_CONTACT_PERSON, Company.class)
                .setParameter("contactPersonId", contactPersonId)
                .getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found entity with contactPersonId: " + contactPersonId));
    }

    @Override
    public List<Company> getAll() {
        return em.createNamedQuery(Company.ALL, Company.class)
                .getResultList();
    }

    @Override
    public List<Company> getAllByRegion(int regionId) {
        return em.createNamedQuery(Company.ALL_BY_REGION, Company.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }

    @Override
    public List<Company> getAllByReliability(Reliability reliability) {
        return em.createNamedQuery(Company.ALL_BY_RELIABILITY, Company.class)
                .setParameter("reliability", reliability)
                .getResultList();
    }

    @Override
    public List<Company> getAllByType(TypeCompany type) {
        return em.createNamedQuery(Company.ALL_BY_TYPE, Company.class)
                .setParameter("typeCompany", type)
                .getResultList();
    }

    @Override
    public List<Company> getAllByTitle(String title) {
        return em.createNamedQuery(Company.ALL_BY_TITLE, Company.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<Company> getAllByAddress(String address) {
        return em.createNamedQuery(Company.ALL_BY_ADDRESS, Company.class)
                .setParameter("address", "%" + address + "%")
                .getResultList();
    }

}
