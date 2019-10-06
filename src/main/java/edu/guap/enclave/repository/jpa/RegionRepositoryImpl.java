package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Region;
import edu.guap.enclave.repository.RegionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class RegionRepositoryImpl implements RegionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Region save(Region region) {
        if (region.isNew()) {
            em.persist(region);
            return region;
        } else {
            return em.merge(region);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Region.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Region get(int id) {
        return em.find(Region.class, id);
    }

    @Override
    public List<Region> getAll() {
        return em.createNamedQuery(Region.ALL_SORTED, Region.class).getResultList();
    }
}
