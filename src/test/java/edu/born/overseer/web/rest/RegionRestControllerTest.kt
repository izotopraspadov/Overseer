package edu.born.overseer.web.rest;

import edu.born.overseer.model.Region;
import edu.born.overseer.repository.RegionRepository;
import edu.born.overseer.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static edu.born.overseer.TestUtil.readFromJsonResultActions;
import static edu.born.overseer.TestUtil.userHttpBasic;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_5;
import static edu.born.overseer.data.RegionTestData.getPreparedCreate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class RegionRestControllerTest extends AbstractControllerTest {

    private static String REST_URL = RegionRestController.REST_URL + '/';

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void create() throws Exception {
        var prepared = getPreparedCreate();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_5)));
        var received = readFromJsonResultActions(action, Region.class);
        System.out.println(JsonUtil.writeValue(prepared));
        prepared.setId(received.getId());

        assertEquals(received, prepared);
    }
}