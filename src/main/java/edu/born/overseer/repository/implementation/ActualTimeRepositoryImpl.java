package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.model.Order;
import edu.born.overseer.repository.ActualTimeRepository;
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
    public ActualTime save(ActualTime actualTime, int orderId) {

        actualTime.setOrder(em.getReference(Order.class, orderId));

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
    public List<ActualTime> getAllByOrder(int orderId) {
        return em.createNamedQuery(ActualTime.ALL_BY_ORDER, ActualTime.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }
}
