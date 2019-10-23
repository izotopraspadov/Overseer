package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.*;
import edu.guap.enclave.repository.OrderedObjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class OrderedObjectRepositoryImpl implements OrderedObjectRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public OrderedObject save(OrderedObject object, int companyId, int groupId, int managerId) {

        object.setCompany(em.getReference(Company.class, companyId));
        object.setGroup(em.getReference(Group.class, groupId));
        object.setManager(em.getReference(Employee.class, managerId));

        if (object.isNew()) {
            em.persist(object);
            return object;
        } else {
            return em.merge(object);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(OrderedObject.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public OrderedObject get(int id) {
        return em.createNamedQuery(OrderedObject.GET, OrderedObject.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public OrderedObject getWithPayments(int id) {
        return em.createNamedQuery(OrderedObject.GET_WITH_PAYMENTS, OrderedObject.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public OrderedObject getWithTasks(int id) {
        return em.createNamedQuery(OrderedObject.GET_WITH_TASKS, OrderedObject.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<OrderedObject> getAll() {
        return em.createNamedQuery(OrderedObject.ALL, OrderedObject.class)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByTitle(String title) {
        return em.createNamedQuery(OrderedObject.ALL_BY_TITLE, OrderedObject.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByCompany(int companyId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_COMPANY, OrderedObject.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByCashless(boolean cashless) {
        return em.createNamedQuery(OrderedObject.ALL_BY_CASHLESS, OrderedObject.class)
                .setParameter("cashless", cashless)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByOrderType(String orderType) {
        return em.createNamedQuery(OrderedObject.ALL_BY_ORDER_TYPE, OrderedObject.class)
                .setParameter("orderType", orderType)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByGroup(int groupId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_GROUP, OrderedObject.class)
                .setParameter("groupId", groupId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByContractExists(boolean contractExists) {
        return em.createNamedQuery(OrderedObject.ALL_BY_CONTRACT_EXISTS, OrderedObject.class)
                .setParameter("contractExists", contractExists)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllBySum(BigDecimal currentSum) {
        return em.createNamedQuery(OrderedObject.ALL_BY_SUM, OrderedObject.class)
                .setParameter("currentSum", currentSum)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByManager(int managerId) {
        return em.createNamedQuery(OrderedObject.ALL_BY_MANAGER, OrderedObject.class)
                .setParameter("managerId", managerId)
                .getResultList();
    }

    @Override
    public List<OrderedObject> getAllByUnderway(boolean underway) {
        return em.createNamedQuery(OrderedObject.ALL_BY_UNDERWAY, OrderedObject.class)
                .setParameter("underway", underway)
                .getResultList();
    }
}
