package edu.born.overseer.web.rest;

import edu.born.overseer.model.Employee;
import edu.born.overseer.repository.EmployeeRepository;
import edu.born.overseer.web.json.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static edu.born.overseer.TestUtil.getMatcher;
import static edu.born.overseer.TestUtil.userHttpBasic;
import static edu.born.overseer.data.EmployeeTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EmployeeProfileRestControllerTest extends AbstractControllerTest {

    private static String REST_URL = EmployeeProfileRestController.REST_URL + '/';

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        employeeRepository.evictCache();
    }

    @Test
    void updateNotAcceptable() throws Exception {
        var prepared = getPreparedUpdate(); // empl 1

        mockMvc.perform(put(REST_URL + EMPLOYEE_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isNotAcceptable());

    }

    @Test
    void update() throws Exception {
        var prepared = getPreparedUpdate(); // empl 1

        mockMvc.perform(put(REST_URL + EMPLOYEE_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isNoContent());


        assertEquals(employeeRepository.getById(prepared.getId()), prepared);
    }

    @Test
    void all() throws Exception {
        mockMvc.perform(get(REST_URL)
//                .param("page", String.valueOf(PAGE_1))
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(Employee.class, EMPLOYEE_4,
                        EMPLOYEE_6,
                        EMPLOYEE_5,
                        EMPLOYEE_1,
                        EMPLOYEE_3));
    }

}