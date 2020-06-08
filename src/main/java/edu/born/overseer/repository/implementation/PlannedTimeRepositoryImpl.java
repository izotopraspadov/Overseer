package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.PlannedTime;
import edu.born.overseer.repository.PlannedTimeRepository;
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
public class PlannedTimeRepositoryImpl implements PlannedTimeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "plannedTime", allEntries = true)
    public PlannedTime save(PlannedTime plannedTime, int orderId, int employeeId) {

        plannedTime.setOrder(em.getReference(Order.class, orderId));
        plannedTime.setEmployee(em.getReference(Employee.class, employeeId));

        if (plannedTime.isNew()) {
            em.persist(plannedTime);
            return plannedTime;
        } else {
            return em.merge(plannedTime);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "plannedTime", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery("PlannedTimed:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public PlannedTime getById(int id) {
        return em.find(PlannedTime.class, id);
    }

    @Override
    @Cacheable("plannedTime")
    public List<PlannedTime> getAllByOrder(int orderId, int first) {
        return em.createNamedQuery("PlannedTime:allByOrder", PlannedTime.class)
                .setParameter("orderId", orderId)
                .setFirstResult(first)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "plannedTime", allEntries = true)
    public void evictCache() {

    }
}
