package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.Employee;
import edu.born.overseer.model.Order;
import edu.born.overseer.model.Task;
import edu.born.overseer.repository.TaskRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
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
    public boolean delete(int id) {
        return em.createNamedQuery("Task:delete")
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Task getById(int id) {
        return em.createNamedQuery("Task:byId", Task.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Task> getAllByOrder(int orderId) {
        return em.createNamedQuery("Task:allByOrder", Task.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

}
