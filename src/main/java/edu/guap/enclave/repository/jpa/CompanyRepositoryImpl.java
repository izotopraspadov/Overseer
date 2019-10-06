package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Company;
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
        return em.find(Company.class, id);
    }

    @Override
    public List<Company> getAll() {
        return em.createNamedQuery(Company.ALL_SORTED, Company.class).getResultList();
    }
}
