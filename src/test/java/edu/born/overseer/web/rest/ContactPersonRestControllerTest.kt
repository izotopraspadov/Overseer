package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.*
import edu.born.overseer.data.COMPANY_1_ID
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_1
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.getPreparedContactPersonCreate
import edu.born.overseer.model.ContactPerson
import edu.born.overseer.repository.ContactPersonRepository
import edu.born.overseer.web.json.JsonUtil.writeValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

internal class ContactPersonRestControllerTest : AbstractControllerTest() {

    companion object {
        private const val REST_URL = ContactPersonRestController.REST_URL + '/'
    }

    @Autowired
    private lateinit var contactPersonRepository: ContactPersonRepository

    @BeforeEach
    fun setUp() {
        contactPersonRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedContactPersonCreate()

        val action = mockMvc.perform(post(REST_URL, COMPANY_1_ID)
                .contentType(APPLICATION_JSON)
                .content(writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_1)))

        val received = readFromJsonResultActions(action, ContactPerson::class.java)
        prepared.id = received.id

        assertEquals(received, prepared)
    }

    @Test
    fun getAllByCompany() {
        mockMvc.perform(get(REST_URL, COMPANY_1_ID)
                .param("page", "$PAGE_1")
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk)
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(getMatcher(ContactPerson::class.java, CONTACT_PERSON_1))
    }
}