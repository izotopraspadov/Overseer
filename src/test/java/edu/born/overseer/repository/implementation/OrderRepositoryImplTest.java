package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import static edu.born.overseer.data.OrderTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() throws Exception {
        orderRepository.evictCache();
    }

    @Test
    void create() {
        var prepared = getPreparedCreate();

        var companyId = prepared.getCompany().getId();
        var groupId = prepared.getGroup().getId();
        var managerId = prepared.getManager().getId();
        var orderTypeId = prepared.getOrderType().getId();

        var savedId = orderRepository
                .save(prepared, companyId, groupId, managerId, orderTypeId)
                .getId();

        var received = orderRepository.getById(savedId);

        assertEquals(received, prepared);
    }

    @Test
    void createWithInvalidPaymentFormat() {
        var prepared = getPreparedCreate();

        prepared.setPaymentFormat("100-100");

        var companyId = prepared.getCompany().getId();
        var groupId = prepared.getGroup().getId();
        var managerId = prepared.getManager().getId();
        var orderTypeId = prepared.getOrderType().getId();

        assertThrows(TransactionSystemException.class, () -> orderRepository.save(prepared, companyId, groupId, managerId, orderTypeId));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();

        var companyId = prepared.getCompany().getId();
        var groupId = prepared.getGroup().getId();
        var managerId = prepared.getManager().getId();
        var orderTypeId = prepared.getOrderType().getId();

        var updated = orderRepository.save(prepared, companyId, groupId, managerId, orderTypeId);

        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        ORDER_1.getTasks().forEach(e -> e.getEmails().forEach(System.out::println));
    }

    @Test
    void deleteNotExecute() {
    }

    @Test
    void getById() {
    }

}