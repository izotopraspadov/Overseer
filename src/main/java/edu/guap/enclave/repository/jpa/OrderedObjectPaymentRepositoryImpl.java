package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.OrderedObjectPayment;
import edu.guap.enclave.repository.OrderedObjectPaymentRepository;
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
}
