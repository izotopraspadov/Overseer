package edu.born.overseer.repository.impl;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.Task;
import edu.born.overseer.repository.TaskRepository;
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
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public Task save(Task task, int orderId, int employeeId) {

        task.setOrder(em.getReference(Order.class, orderId));
        task.setResponsible(em.getReference(Employee.class, employeeId));

        if (task.isNew()) {
            em.persist(task);
            return task;
        } else {
            return em.merge(task);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "tasks", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(Task.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Task getById(int id) {
        return em.find(Task.class, id);
    }

    @Override
    @Cacheable("tasks")
    public List<Task> getAllByOrder(Integer page, Integer orderId) {
        return em.createNamedQuery(Task.ALL_BY_ORDER, Task.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("orderId", orderId)
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void evictCache() {

    }
}
