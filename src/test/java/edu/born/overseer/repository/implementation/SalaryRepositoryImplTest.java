package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.SalaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1_ID;
import static edu.born.overseer.data.SalaryTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class SalaryRepositoryImplTest {

    @Autowired
    private SalaryRepository salaryRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();

        var current = salaryRepository.getCurrentByEmployee(EMPLOYEE_1_ID)
                .endDate(LocalDate.now());

        salaryRepository.save(current, EMPLOYEE_1_ID);

        var savedId = salaryRepository.save(prepared, EMPLOYEE_1_ID)
                .getId();

        prepared.setId(savedId);

        assertThat(salaryRepository.getAllByEmployee(EMPLOYEE_1_ID), contains(prepared, current, SALARY_1));
    }

    @Test
    void delete() {
        assertThrows(DataIntegrityViolationException.class, () -> salaryRepository.save(getPreparedDuplicate(), EMPLOYEE_1_ID));
    }

    @Test
    void getCurrentByEmployee() {
        var received = salaryRepository.getCurrentByEmployee(EMPLOYEE_1_ID);

        assertNull(received.getEndDate());
    }

    @Test
    void getAllByEmployee() {
        assertThat(salaryRepository.getAllByEmployee(EMPLOYEE_1_ID), contains(SALARY_7, SALARY_1));
    }

}