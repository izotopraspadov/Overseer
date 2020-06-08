package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.Region;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.repository.CompanyRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "companies", allEntries = true)
    public Company save(Company company, int regionId) {

        company.setRegion(em.getReference(Region.class, regionId));

        if (company.isNew()) {
            em.persist(company);
            return company;
        } else {
            return em.merge(company);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "companies", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery("Company:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Company getById(int id) {
        return em.find(Company.class, id);
    }

    @Override
    public Company getByContactPersonId(int contactPersonId) {
        return em.createNamedQuery("Company:byContactPerson", Company.class)
                .setParameter("contactPersonId", contactPersonId)
                .getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found entity with contactPersonId: " + contactPersonId));
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAll(int first) {
        return em.createNamedQuery("Company:all", Company.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByRegion(int regionId, int first) {
        return em.createNamedQuery("Company:allByRegion", Company.class)
                .setParameter("regionId", regionId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByReliability(ReliabilityType reliabilityType, int first) {
        return em.createNamedQuery("Company:allByReliability", Company.class)
                .setParameter("reliability", reliabilityType)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByType(CompanyType type, int first) {
        return em.createNamedQuery("Company:allByType", Company.class)
                .setParameter("typeCompany", type)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByTitle(String title, int first) {
        return em.createNamedQuery("Company:allByTitle", Company.class)
                .setParameter("title", title)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByAddress(String address, int first) {
        return em.createNamedQuery("Company:allByAddress", Company.class)
                .setParameter("address", address)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAllByItn(String itn, int first) {
        return em.createNamedQuery("Company:allByItn", Company.class)
                .setParameter("itn", itn)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "companies", allEntries = true)
    public void evictCache() {

    }
}
