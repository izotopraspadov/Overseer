package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.ActualTime;
import edu.guap.enclave.model.OrderedObject;
import edu.guap.enclave.repository.ActualTimeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class ActualTimeRepositoryImpl implements ActualTimeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ActualTime save(ActualTime actualTime, int orderedObjectId) {

        actualTime.setOrderedObject(em.getReference(OrderedObject.class, orderedObjectId));

        if (actualTime.isNew()) {
            em.persist(actualTime);
            return actualTime;
        } else {
            return em.merge(actualTime);
        }

    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(ActualTime.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<ActualTime> getAllByOrderedObject(int orderedObjectId) {
        return em.createNamedQuery(ActualTime.ALL_BY_ORDERED_OBJECT, ActualTime.class)
                .setParameter("orderedObjectId", orderedObjectId)
                .getResultList();
    }
}
