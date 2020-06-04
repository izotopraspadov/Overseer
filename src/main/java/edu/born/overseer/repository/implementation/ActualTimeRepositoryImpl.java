package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.repository.ActualTimeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class ActualTimeRepositoryImpl implements ActualTimeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ActualTime save(ActualTime actualTime, int orderId, int employeeId) {

        actualTime.setOrder(em.getReference(Order.class, orderId));
        actualTime.setEmployee(em.getReference(Employee.class, employeeId));

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
        return em.createNamedQuery("ActualTime:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ActualTime getById(int id) {
        return em.find(ActualTime.class, id);
    }

    @Override
    public List<ActualTime> getAllByOrder(int orderId, int first) {
        return em.createNamedQuery("ActualTime:allByOrder", ActualTime.class)
                .setParameter("orderId", orderId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

}
