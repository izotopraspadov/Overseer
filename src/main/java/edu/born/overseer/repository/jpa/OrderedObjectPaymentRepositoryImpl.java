package edu.born.overseer.repository.jpa;

import edu.born.overseer.model.OrderedObjectPayment;
import edu.born.overseer.repository.OrderedObjectPaymentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderedObjectPaymentRepositoryImpl implements OrderedObjectPaymentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<OrderedObjectPayment> getAllByDate(LocalDate date) {
        return em.createNamedQuery(OrderedObjectPayment.ALL_BY_DATE, OrderedObjectPayment.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<OrderedObjectPayment> getAllByOrderedObject(int orderedObjectId) {
        return em.createNamedQuery(OrderedObjectPayment.ALL_BY_ORDERED_OBJECT, OrderedObjectPayment.class)
                .setParameter("orderedObjectId", orderedObjectId)
                .getResultList();
    }

    @Override
    public List<OrderedObjectPayment> getAll() {
        return em.createNamedQuery(OrderedObjectPayment.ALL, OrderedObjectPayment.class)
                .getResultList();
    }
}
