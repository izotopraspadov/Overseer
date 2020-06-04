package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

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
        return em.find(Region.class, id);
    }


    @Override
    public List<Region> getAll(int first) {
        return em.createNamedQuery("Region:all", Region.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    public List<Region> getAllByTitle(String title, int first) {
        return em.createNamedQuery("Region:allByTitle", Region.class)
                .setParameter("title", title)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

}
