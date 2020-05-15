package edu.born.overseer.repository.implementation;

import edu.born.overseer.data.RegionTestData;
import edu.born.overseer.data.SalaryTestData;
import edu.born.overseer.model.Salary;
import edu.born.overseer.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static edu.born.overseer.data.EmailTestData.EMPLOYEE_1_EMAILS;
import static edu.born.overseer.data.EmployeeTestData.*;
import static edu.born.overseer.data.PhoneTestData.EMPLOYEE_1_PHONES;
import static edu.born.overseer.data.SalaryTestData.SALARY_1;
import static edu.born.overseer.data.SalaryTestData.SALARY_7;
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
    void createNewSalary() {
        var received = employeeRepository.getById(EMPLOYEE_1_ID);

        // finish current salary
        SalaryTestData.finishCurrentSalary(received.getSalary());

        // create new
        var newSalary = SalaryTestData.getPreparedCreate();

        received.getSalary().add(newSalary);

        // update
        employeeRepository
                .save(received, received.getRegion().getId());

        var updatedSalarySet = employeeRepository.getById(EMPLOYEE_1_ID)
                .getSalary();

        var oldSalary = new Salary(SALARY_7).endDate(LocalDate.now());

        assertThat(updatedSalarySet, contains(SalaryTestData.NEXT_SALARY, oldSalary, SALARY_1));
    }

    @Test
    void createDuplicateSalary() {
        var received = employeeRepository.getById(EMPLOYEE_1_ID);

        var duplicate = SalaryTestData.getPreparedDuplicate();

        received.getSalary().add(duplicate);

        assertThrows(DataIntegrityViolationException.class, () -> employeeRepository.save(received, received.getRegion().getId()));
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
        var received = employeeRepository.getById(EMPLOYEE_1_ID);

        assertEquals(received, EMPLOYEE_1);
        assertEquals(received.getEmails(), EMPLOYEE_1_EMAILS);
        assertEquals(received.getPhones(), EMPLOYEE_1_PHONES);
        assertEquals(received.getSalary(), SalaryTestData.EMPLOYEE_1_SALARIES);
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NoResultException.class, () -> employeeRepository.getById(INVALID_ID));
    }

    @Test
    void getByLogin() {
        assertEquals(employeeRepository.getByLogin(EMPLOYEE_1_LOGIN), EMPLOYEE_1);
    }

    @Test
    void getByLoginNotFound() {
        assertThrows(NoResultException.class, () -> employeeRepository.getByLogin(INVALID_LOGIN));
    }

    @Test
    void getAll() {
        assertThat(employeeRepository.getAll(), contains(EMPLOYEE_4,
                EMPLOYEE_6,
                EMPLOYEE_5,
                EMPLOYEE_1,
                EMPLOYEE_3,
                EMPLOYEE_2)
        );
    }

    @Test
    void getAllByRegion() {
        assertThat(employeeRepository.getAllByRegion(RegionTestData.REGION_1_ID), contains(EMPLOYEE_1, EMPLOYEE_2));
    }

    @Test
    void getAllByAddress() {
        assertThat(employeeRepository.getAllByAddress(EMPLOYEE_1.getAddress()), contains(EMPLOYEE_1));
    }

    @Test
    void getAllByAddressPartialMatch() {
        assertThat(employeeRepository.getAllByAddress("ул."), contains(EMPLOYEE_4, EMPLOYEE_6, EMPLOYEE_1));
    }

    @Test
    void getAllByFullName() {
        assertThat(employeeRepository.getAllByFullName(EMPLOYEE_1.getFullName()), contains(EMPLOYEE_1));
    }

    @Test
    void getAllByFullNamePartialMatch() {
        assertThat(employeeRepository.getAllByFullName("Роман"), contains(EMPLOYEE_1, EMPLOYEE_3));
    }

}