package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1_ID;
import static edu.born.overseer.data.OrderTestData.ORDER_1_ID;
import static edu.born.overseer.data.TaskTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID).getId();

        prepared.setId(savedId);

        assertEquals(taskRepository.getById(savedId), prepared);
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();
        var updated = taskRepository.save(prepared, ORDER_1_ID, EMPLOYEE_1_ID);

        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        assertEquals(taskRepository.delete(TASK_1_ID), Boolean.TRUE);
        assertThat(taskRepository.getAllByOrder(ORDER_1_ID), not(contains(TASK_1)));
    }

    @Test
    void getById() {
    }

    @Test
    void getAllByOrder() {
    }

}