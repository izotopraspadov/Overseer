package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionSystemException;

import static edu.born.overseer.OrderTestData.getPreparedCreate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

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

        prepared.setId(savedId);

        assertEquals(orderRepository.getById(savedId), prepared);
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
    }

    @Test
    void delete() {
    }

    @Test
    void deleteNotExecute() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByIdNotFound() {
    }

    @Test
    void getByIdWithPayments() {
    }

    @Test
    void getByIdWithTasks() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByTitle() {
    }

    @Test
    void getAllByCompany() {
    }

    @Test
    void getAllByCashless() {
    }

    @Test
    void getAllByOrderType() {
    }

    @Test
    void getAllByGroup() {
    }

    @Test
    void getAllByContractIsNeed() {
    }

    @Test
    void getAllByContractExists() {
    }

    @Test
    void getAllByPlannedStartDate() {
    }

    @Test
    void getAllByActualStartDate() {
    }

    @Test
    void getAllByPlannedEndDate() {
    }

    @Test
    void getAllByActualEndDate() {
    }

    @Test
    void getAllBySum() {
    }

    @Test
    void getAllByManager() {
    }

    @Test
    void getAllByUnderway() {
    }

    @Test
    void getAllByExpectedPayment() {
    }

    @Test
    void getAllByPaymentFormat() {
    }

    @Test
    void getAllByNumberOfLines() {
    }

}