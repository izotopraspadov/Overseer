package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Task;
import edu.guap.enclave.repository.TaskRepository;
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
    public List<Task> getAllByOrderedObject(int orderedObjectId) {
        return em.createNamedQuery(Task.ALL_BY_ORDERED_OBJECT, Task.class)
                .setParameter("orderedObjectId", orderedObjectId)
                .getResultList();
    }

}
