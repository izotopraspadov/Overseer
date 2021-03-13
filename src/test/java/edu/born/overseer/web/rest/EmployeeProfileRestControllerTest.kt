package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.*
import edu.born.overseer.data.EMPLOYEE_1_ID
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_2
import edu.born.overseer.data.REGION_1_ID
import edu.born.overseer.model.Employee
import edu.born.overseer.repository.EmployeeRepository
import edu.born.overseer.web.json.JsonUtil.writeValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class EmployeeProfileRestControllerTest : AbstractControllerTest() {

    companion object {
        private const val REST_URL = EmployeeProfileRestController.REST_URL + '/'
    }

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @BeforeEach
    fun setUp() {
        employeeRepository.evictCache()
    }

    @Test
    fun updateNotAcceptable() {
        val prepared = Employee(EMPLOYEE_1).apply {
            fullName = "Updated employee"
        }

        mockMvc.perform(put(REST_URL + EMPLOYEE_1_ID)
                .contentType(APPLICATION_JSON)
                .content(writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_2)))
                .andExpect(status().isNotAcceptable)
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
    fun all() {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .param("page", "$PAGE_1")
                .param("region_id", "$REGION_1_ID")
                .param("full_name", "Rom")
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(Employee::class.java, EMPLOYEE_1))
    }
}