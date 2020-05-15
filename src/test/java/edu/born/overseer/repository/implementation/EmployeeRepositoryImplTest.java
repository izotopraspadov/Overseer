package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class EmployeeRepositoryImplTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();

        var savedId = employeeRepository
                .save(prepared, prepared.getRegion().getId())
                .getId();

        var received = employeeRepository.getById(savedId);

        assertEquals(received, prepared);
        assertEquals(List.copyOf(received.getEmails()), List.copyOf(prepared.getEmails()));
        assertEquals(List.copyOf(received.getPhones()), List.copyOf(prepared.getPhones()));
    }

    @Test
    void createDuplicate() {
        var prepared = getPreparedDuplicate();
        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.save(prepared, prepared.getRegion().getId()));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();

        var updatedId = employeeRepository
                .save(prepared, prepared.getRegion().getId())
                .getId();

        var received = employeeRepository.getById(updatedId);

        assertEquals(received, prepared);
        assertEquals(Set.copyOf(received.getEmails()), Set.copyOf(prepared.getEmails()));
        assertEquals(Set.copyOf(received.getPhones()), Set.copyOf(prepared.getPhones()));
    }

    @Test
    void delete() {
        assertEquals(employeeRepository.delete(EMPLOYEE_1_ID), Boolean.TRUE);
        assertThat(employeeRepository.getAll(), not(contains(EMPLOYEE_1)));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(employeeRepository.delete(INVALID_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(employeeRepository.getById(EMPLOYEE_1_ID), EMPLOYEE_1);
    }

    @Test
    void getByIdNotFound() {
    }

    @Test
    void getByLogin() {
    }

    @Test
    void getByLoginNotFound() {
    }

    @Test
    void getWithSalaryAndContacts() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByRegion() {
    }

    @Test
    void getAllByAddress() {
    }

    @Test
    void getAllByAddressPartialMatch() {
    }

    @Test
    void getAllByFullName() {
    }

    @Test
    void getAllByFullNamePartialMatch() {
    }

}