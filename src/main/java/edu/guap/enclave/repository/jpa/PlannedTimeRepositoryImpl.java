package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.OrderedObject;
import edu.guap.enclave.model.PlannedTime;
import edu.guap.enclave.repository.PlannedTimeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PlannedTimeRepositoryImpl implements PlannedTimeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public PlannedTime save(PlannedTime plannedTime, int orderedObjectId) {

        plannedTime.setOrderedObject(em.getReference(OrderedObject.class, orderedObjectId));

        if (plannedTime.isNew()) {
            em.persist(plannedTime);
            return plannedTime;
        } else {
            return em.merge(plannedTime);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(PlannedTime.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public List<PlannedTime> getAllByOrderedObject(int orderedObjectId) {
        return em.createNamedQuery(PlannedTime.ALL_BY_ORDERED_OBJECT, PlannedTime.class)
                .setParameter("orderedObjectId", orderedObjectId)
                .getResultList();
    }
}
