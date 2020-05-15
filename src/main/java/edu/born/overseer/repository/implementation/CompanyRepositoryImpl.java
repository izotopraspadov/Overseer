package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Company;
import edu.born.overseer.model.Region;
import edu.born.overseer.model.ReliabilityType;
import edu.born.overseer.model.CompanyType;
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
    public boolean delete(int id) {
        return em.createNamedQuery("Company:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Company getById(int id) {
        return em.createNamedQuery("Company:byId", Company.class)
                .setParameter("id", id)
                .getSingleResult();
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
    public List<Company> getAll() {
        return em.createNamedQuery("Company:all", Company.class)
                .getResultList();
    }

    @Override
    public List<Company> getAllByRegion(int regionId) {
        return em.createNamedQuery("Company:allByRegion", Company.class)
                .setParameter("regionId", regionId)
                .getResultList();
    }

    @Override
    public List<Company> getAllByReliability(ReliabilityType reliabilityType) {
        return em.createNamedQuery("Company:allByReliability", Company.class)
                .setParameter("reliability", reliabilityType)
                .getResultList();
    }

    @Override
    public List<Company> getAllByType(CompanyType type) {
        return em.createNamedQuery("Company:allByType", Company.class)
                .setParameter("typeCompany", type)
                .getResultList();
    }

    @Override
    public List<Company> getAllByTitle(String title) {
        return em.createNamedQuery("Company:allByTitle", Company.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Company> getAllByAddress(String address) {
        return em.createNamedQuery("Company:allByAddress", Company.class)
                .setParameter("address", address)
                .getResultList();
    }

    @Override
    public List<Company> getAllByItb(String itn) {
        return em.createNamedQuery("Company:allByItn", Company.class)
                .setParameter("itn", itn)
                .getResultList();
    }

}
