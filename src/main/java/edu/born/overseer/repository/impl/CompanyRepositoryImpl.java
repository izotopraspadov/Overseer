package edu.born.overseer.repository.impl;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.CompanyType;
import edu.born.overseer.model.Region;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.repository.CompanyRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
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
        return em.createNamedQuery(Company.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Company getById(int id) {
        return em.find(Company.class, id);
    }

    @Override
    @Cacheable("companies")
    public List<Company> getAll(Integer page,
                                Integer contactPersonId,
                                Integer regionId,
                                ReliabilityType reliabilityType,
                                CompanyType type,
                                String title,
                                String address,
                                String itn) {

        return em.createNamedQuery(Company.ALL, Company.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("contactPersonId", contactPersonId)
                .setParameter("regionId", regionId)
                .setParameter("reliability", reliabilityType)
                .setParameter("typeCompany", type)
                .setParameter("title", Objects.toString(title, ""))
                .setParameter("address", Objects.toString(address, ""))
                .setParameter("itn", Objects.toString(itn, ""))
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "companies", allEntries = true)
    public void evictCache() {

    }
}
