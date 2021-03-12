package edu.born.overseer.repository.impl;

import edu.born.overseer.model.ActualTime;
import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.repository.ActualTimeRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class ActualTimeRepositoryImpl implements ActualTimeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "actualTime", allEntries = true)
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
    @CacheEvict(value = "actualTime", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(ActualTime.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public ActualTime getById(int id) {
        return em.find(ActualTime.class, id);
    }

    @Override
    @Cacheable("actualTime")
    public List<ActualTime> getAllByOrder(Integer page, Integer orderId) {
        return em.createNamedQuery(ActualTime.ALL_BY_ORDER, ActualTime.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("orderId", orderId)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "actualTime", allEntries = true)
    public void evictCache() {

    }
}
