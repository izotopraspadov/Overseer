package edu.born.overseer.repository.impl

import edu.born.overseer.data.*
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.model.Email
import edu.born.overseer.model.Employee
import edu.born.overseer.model.Phone
import edu.born.overseer.repository.EmployeeRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.acls.model.NotFoundException
import java.lang.Boolean.FALSE

internal class EmployeeRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @BeforeEach
    fun setUp() {
        employeeRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedEmployeeCreate()
        val savedId = employeeRepository.save(prepared, prepared.region.id).id
        val received = employeeRepository.getById(savedId)

        assertEquals(received, prepared)
        assertEquals(HashSet<Email>(received.emails), HashSet<Email>(prepared.emails))
        assertEquals(HashSet<Phone>(received.phones), HashSet<Phone>(prepared.phones))
    }

    @Test
    fun createDuplicate() {
        val prepared = getPreparedEmployeeCreate().apply {
            login = EMPLOYEE_1.login
        }

        Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            employeeRepository.save(prepared, prepared.region.id)
        }
    }

    @Test
    fun update() {
        val prepared = Employee(EMPLOYEE_1).apply {
            fullName = "Updated employee"
        }
        val updatedId = employeeRepository.save(prepared, prepared.region.id).id
        val received = employeeRepository.getById(updatedId)

        assertEquals(received, prepared)
    }

    @Test(expected = NotFoundException::class)
    fun delete() {
        employeeRepository.delete(EMPLOYEE_1_ID)
        employeeRepository.getById(EMPLOYEE_1_ID)
    }

    @Test
    fun deleteNotExecute() {
        assertEquals(employeeRepository.delete(INVALID_ID), FALSE)
    }

    @Test
    fun getById() {
        val received = employeeRepository.getById(EMPLOYEE_1_ID)

        assertEquals(received, EMPLOYEE_1)
        assertEquals(received.emails, EMPLOYEE_1_EMAILS)
        assertEquals(received.phones, EMPLOYEE_1_PHONES)
        assertEquals(received.salary, EMPLOYEE_1_SALARIES.filter { it.endDate == null }.toSet())
    }

    @Test
    fun getByLogin() {
        assertEquals(employeeRepository.getByLogin(EMPLOYEE_1.login), EMPLOYEE_1)
    }

    @Test
    fun getAll() {
        // by page, regionId, address, fullName
        assertThat(employeeRepository.getAll(1, REGION_1_ID, "Zastavs", "Romano"), contains(EMPLOYEE_1))
    }
}