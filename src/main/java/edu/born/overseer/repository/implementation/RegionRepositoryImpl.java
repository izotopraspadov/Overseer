package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
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
public class RegionRepositoryImpl implements RegionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "regions", allEntries = true)
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
    @CacheEvict(value = "regions", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(Region.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Region getById(int id) {
        return em.find(Region.class, id);
    }

    @Override
    @Cacheable("regions")
    public List<Region> getAll(Integer page, String title) {
        return em.createNamedQuery(Region.ALL, Region.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("title", Objects.toString(title, ""))
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "regions", allEntries = true)
    public void evictCache() {

    }
}
