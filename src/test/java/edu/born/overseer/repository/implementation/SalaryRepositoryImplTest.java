package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.SalaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1_ID;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class SalaryRepositoryImplTest {

    @Autowired
    private SalaryRepository salaryRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void getCurrentByEmployee() {
        var received = salaryRepository.getCurrent(EMPLOYEE_1_ID);
        assertNull(received.getEndDate());
    }

    @Test
    void getAllByEmployee() {
    }

}