package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.SalaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static edu.born.overseer.TestUtil.unlimitedPageLength;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1_ID;
import static edu.born.overseer.data.SalaryTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertNull;

class SalaryRepositoryImplTest extends AbstractRepositoryTest {

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

        assertThat(salaryRepository.getAllByEmployee(EMPLOYEE_1_ID, unlimitedPageLength()), contains(prepared, current, SALARY_1));
    }

    @Test
    void delete() {
        salaryRepository.delete(SALARY_1_ID);
        assertThat(salaryRepository.getAllByEmployee(EMPLOYEE_1_ID, unlimitedPageLength()), not(contains(SALARY_1)));
    }

    @Test
    void getCurrentByEmployee() {
        var received = salaryRepository.getCurrentByEmployee(EMPLOYEE_1_ID);
        assertNull(received.getEndDate());
    }

    @Test
    void getAllByEmployee() {
        assertThat(salaryRepository.getAllByEmployee(EMPLOYEE_1_ID, unlimitedPageLength()), contains(SALARY_7, SALARY_1));
    }

}