package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Group;
import edu.born.overseer.repository.GroupRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
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
    public boolean delete(int id) {
        return em.createNamedQuery("Group:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Group getById(int id) {
        return em.createNamedQuery("Group:byId", Group.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Group> getAll() {
        return em.createNamedQuery("Group:all", Group.class)
                .getResultList();
    }

}
