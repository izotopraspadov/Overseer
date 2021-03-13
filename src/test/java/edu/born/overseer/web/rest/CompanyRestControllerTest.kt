package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.*
import edu.born.overseer.data.COMPANY_1_ID
import edu.born.overseer.data.CompanyData.COMPANY_1
import edu.born.overseer.data.CompanyData.COMPANY_2
import edu.born.overseer.data.CompanyData.COMPANY_3
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.REGION_1_ID
import edu.born.overseer.model.Company
import edu.born.overseer.repository.CompanyRepository
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class CompanyRestControllerTest : AbstractControllerTest() {

    companion object {
        private const val REST_URL = CompanyRestController.REST_URL + '/'
    }

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @BeforeEach
    fun setUp() {
        companyRepository.evictCache()
    }

    @Test
    @Throws(Exception::class)
    fun delete() {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + COMPANY_1_ID)
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isNoContent)

        assertNull(companyRepository.getById(COMPANY_1_ID))
    }

    @Test
    @Throws(Exception::class)
    fun getById() {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + COMPANY_1_ID)
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(Company::class.java, COMPANY_1))
    }

    @Test
    @Throws(Exception::class)
    fun getAllByParams() {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .param("page", "$PAGE_1")
                .param("region_id", "$REGION_1_ID")
                .param("itn", "000000000000")
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(Company::class.java, COMPANY_1))
    }

    @Test
    @Throws(Exception::class)
    fun getAll() {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .param("page", "$PAGE_1")
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(Company::class.java, COMPANY_2, COMPANY_1, COMPANY_3))
    }
}