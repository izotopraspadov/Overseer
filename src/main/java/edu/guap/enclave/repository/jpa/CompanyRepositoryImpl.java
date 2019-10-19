package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Company;
import edu.guap.enclave.model.Reliability;
import edu.guap.enclave.model.TypeCompany;
import edu.guap.enclave.repository.CompanyRepository;
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
    public Company get(int id) {
        return em.createNamedQuery(Company.GET, Company.class)
                .setParameter("id", id)
                .getSingleResult();
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
                .setParameter("reliability", reliability.toString())
                .getResultList();
    }

    @Override
    public List<Company> getAllByType(TypeCompany type) {
        return em.createNamedQuery(Company.ALL_BY_TYPE, Company.class)
                .setParameter("typeCompany", type.toString())
                .getResultList();
    }

    @Override
    public Company findByItb(String itn) {
        return em.createNamedQuery(Company.FIND_BY_ITN, Company.class)
                .setParameter("itn", itn)
                .getSingleResult();
    }

    @Override
    public Company findByAddress(String address) {
        return em.createNamedQuery(Company.FIND_BY_ADDRESS, Company.class)
                .setParameter("address", address)
                .getSingleResult();
    }

    @Override
    public Company findByTitle(String title) {
        return em.createNamedQuery(Company.FIND_BY_TITLE, Company.class)
                .setParameter("title", title)
                .getSingleResult();
    }

}
