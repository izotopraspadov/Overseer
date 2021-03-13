package edu.born.overseer.repository.impl

import edu.born.overseer.TestUtil
import edu.born.overseer.TestUtil.PAGE_1
import edu.born.overseer.data.*
import edu.born.overseer.repository.SalaryRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.core.IsNot.not
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate.now

internal class SalaryRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var salaryRepository: SalaryRepository

    @BeforeEach
    fun setUp() {
        salaryRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedSalaryCreate()
        val current = salaryRepository.getCurrentByEmployee(EMPLOYEE_1_ID).apply { endDate = now() }

        salaryRepository.save(current, EMPLOYEE_1_ID)

        val savedId = salaryRepository.save(prepared, EMPLOYEE_1_ID).id
        prepared.id = savedId

        val updatedSalaries = salaryRepository.getAllByEmployee(PAGE_1, EMPLOYEE_1_ID)

        assertThat(updatedSalaries, contains(prepared, current, SALARY_1))
    }

    @Test
    fun delete() {
        salaryRepository.delete(SALARY_1_ID)

        val salaries = salaryRepository.getAllByEmployee(PAGE_1, EMPLOYEE_1_ID)

        assertThat(salaries, not(contains(SALARY_1)))
    }

    @Test
    fun getCurrentByEmployee() {
        val received = salaryRepository.getCurrentByEmployee(EMPLOYEE_1_ID)

        assertNull(received.endDate)
    }

    @Test
    fun getAllByEmployee() {
        val salaries = salaryRepository.getAllByEmployee(PAGE_1, EMPLOYEE_1_ID)

        assertThat(salaries, contains(SALARY_2, SALARY_1))
    }
}