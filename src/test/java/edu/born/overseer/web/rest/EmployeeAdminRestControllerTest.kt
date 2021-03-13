package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.readFromJsonResultActions
import edu.born.overseer.TestUtil.userHttpBasic
import edu.born.overseer.data.EMPLOYEE_1_ID
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.getPreparedEmployeeCreate
import edu.born.overseer.model.Employee
import edu.born.overseer.repository.EmployeeRepository
import edu.born.overseer.web.json.JsonUtil.writeValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class EmployeeAdminRestControllerTest : AbstractControllerTest() {

    companion object {
        private const val REST_URL = EmployeeAdminRestController.REST_URL + '/'
    }

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @BeforeEach
    fun setUp() {
        employeeRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedEmployeeCreate()

        val action = mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_1)))

        val received = readFromJsonResultActions(action, Employee::class.java)
        prepared.id = received.id

        assertEquals(received, prepared)
    }

    @Test
    fun update() {
        val prepared = Employee(EMPLOYEE_1).apply {
            fullName = "Updated employee"
        }
        mockMvc.perform(put(REST_URL + EMPLOYEE_1_ID)
                .contentType(APPLICATION_JSON)
                .content(writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isNoContent)

        assertEquals(employeeRepository.getById(prepared.id), prepared)
    }

    @Test
    fun delete_() {
        mockMvc.perform(delete(REST_URL + EMPLOYEE_1_ID)
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isNoContent)

        assertNull(employeeRepository.getById(EMPLOYEE_1_ID))
    }

    @Test
    fun deleteNoRights() {
        mockMvc.perform(delete(REST_URL + EMPLOYEE_1_ID)
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isForbidden)
    }
}