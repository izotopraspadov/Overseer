package edu.born.overseer.web.rest

import edu.born.overseer.TestUtil.readFromJsonResultActions
import edu.born.overseer.TestUtil.userHttpBasic
import edu.born.overseer.data.EmployeeData.EMPLOYEE_1
import edu.born.overseer.data.getPreparedRegionCreate
import edu.born.overseer.model.Region
import edu.born.overseer.repository.RegionRepository
import edu.born.overseer.web.json.JsonUtil.writeValue
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

internal class RegionRestControllerTest : AbstractControllerTest() {

    companion object {
        private const val REST_URL = RegionRestController.REST_URL + '/'
    }

    @Autowired
    private lateinit var regionRepository: RegionRepository

    @Test
    fun create() {
        val prepared = getPreparedRegionCreate()

        val action = mockMvc.perform(post(REST_URL)
                .contentType(APPLICATION_JSON)
                .content(writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_1)))

        val received = readFromJsonResultActions(action, Region::class.java)
        prepared.id = received.id

        assertEquals(received, prepared)
    }
}