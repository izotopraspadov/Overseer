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
        return em.createNamedQuery("Region:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Region getById(int id) {
        return em.createNamedQuery("Region:byId", Region.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    @Override
    public List<Region> getAll() {
        return em.createNamedQuery("Region:all", Region.class)
                .getResultList();
    }

    @Override
    public List<Region> getAllByTitle(String title) {
        return em.createNamedQuery("Region:allByTitle", Region.class)
                .setParameter("title", title)
                .getResultList();
    }

}
