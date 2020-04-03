package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getWithPayments() {
    }


    @Test
    void getWithTasks() {
      //  orderedObjectRepository.getWithTasks(100103).getTasks().forEach(System.out::println);
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
    void getAllByContractExists() {
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
}