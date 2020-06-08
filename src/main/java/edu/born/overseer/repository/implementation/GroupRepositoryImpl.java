package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Group;
import edu.born.overseer.repository.GroupRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "groups", allEntries = true)
    public Group save(Group group) {
        if (group.isNew()) {
            em.persist(group);
            return group;
        } else {
            return em.merge(group);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "groups", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery("Group:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Group getById(int id) {
        return em.find(Group.class, id);
    }

    @Override
    @Cacheable("groups")
    public List<Group> getAll(int first) {
        return em.createNamedQuery("Group:all", Group.class)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "groups", allEntries = true)
    public void evictCache() {

    }
}
