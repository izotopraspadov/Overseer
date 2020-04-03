package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Order;
import edu.born.overseer.model.PlannedTime;
import edu.born.overseer.repository.PlannedTimeRepository;
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
    public PlannedTime save(PlannedTime plannedTime, int orderId) {

        plannedTime.setOrder(em.getReference(Order.class, orderId));

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
    public List<PlannedTime> getAllByOrder(int orderId) {
        return em.createNamedQuery(PlannedTime.ALL_BY_ORDER, PlannedTime.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
