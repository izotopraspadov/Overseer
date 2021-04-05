package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.getMatcher
import edu.born.overseer.TestUtil.userHttpBasic
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.PAGE_1
import edu.born.overseer.data.REGION_1_ID
import edu.born.overseer.model.Employee
import edu.born.overseer.repository.EmployeeRepository
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
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
    fun all() {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .param("page", "$PAGE_1")
                .param("region_id", "$REGION_1_ID")
                .param("full_name", "Rom")
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(Employee::class.java, *listOf(EMPLOYEE_1).toTypedArray()))
    }
}