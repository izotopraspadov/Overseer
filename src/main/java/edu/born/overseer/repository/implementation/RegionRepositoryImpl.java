package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
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
    public Region getById(int id) {
        return em.createNamedQuery(Region.BY_ID, Region.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Region getByTitle(String title) {
        return em.createNamedQuery(Region.BY_TITLE, Region.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public List<Region> getAll() {
        return em.createNamedQuery(Region.ALL, Region.class)
                .getResultList();
    }

}
