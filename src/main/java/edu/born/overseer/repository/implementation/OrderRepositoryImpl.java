package edu.born.overseer.repository.implementation;

import edu.born.overseer.model.*;
import edu.born.overseer.repository.OrderRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static edu.born.overseer.util.PageUtil.getFirstByPage;
import static edu.born.overseer.util.PageUtil.getPageLength;

@Repository
@Transactional(readOnly = true)
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    @CacheEvict(value = "orders", allEntries = true)
    public Order save(Order order, int companyId, int groupId, int managerId, int orderTypeId) {

        order.setCompany(em.getReference(Company.class, companyId));
        order.setGroup(em.getReference(Group.class, groupId));
        order.setManager(em.getReference(Employee.class, managerId));
        order.setOrderType(em.getReference(OrderType.class, orderTypeId));

        if (order.isNew()) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = "orders", allEntries = true)
    public boolean delete(int id) {
        return em.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Order getById(int id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> getAll(Integer page,
                              Integer companyId,
                              Boolean cashless,
                              Integer groupId,
                              Boolean contractIsNeed,
                              Boolean contractExists,
                              LocalDate plannedStartDate,
                              LocalDate actualStartDate,
                              LocalDate plannedEndDate,
                              LocalDate actualEndDate,
                              BigDecimal currentSum,
                              Integer managerId,
                              Boolean underway,
                              BigDecimal expectedPayment,
                              Integer numberOfLines,
                              String format,
                              String title) {

        return em.createNamedQuery(Order.ALL, Order.class)
                .setFirstResult(getFirstByPage(page))
                .setParameter("companyId", companyId)
                .setParameter("cashless", cashless)
                .setParameter("groupId", groupId)
                .setParameter("contractIsNeed", contractIsNeed)
                .setParameter("contractExists", contractExists)
                .setParameter("plannedStartDate", plannedStartDate)
                .setParameter("actualStartDate", actualStartDate)
                .setParameter("plannedEndDate", plannedEndDate)
                .setParameter("actualEndDate", actualEndDate)
                .setParameter("currentSum", currentSum)
                .setParameter("managerId", managerId)
                .setParameter("underway", underway)
                .setParameter("expectedPayment", expectedPayment)
                .setParameter("numberOfLines", numberOfLines)
                .setParameter("paymentFormat", Objects.toString(format, "")) // for like %%
                .setParameter("title", Objects.toString(title, "")) // for like %%
                .setMaxResults(getPageLength())
                .getResultList();
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public void evictCache() {

    }
}
