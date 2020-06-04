package edu.born.overseer.web.rest;

import edu.born.overseer.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static edu.born.overseer.TestUtil.*;
import static edu.born.overseer.data.CompanyTestData.*;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_5;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompanyRestControllerTest extends AbstractControllerTest {

    private static String REST_URL = CompanyRestController.REST_URL + '/';

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
    }

    @Test
    void getByContactPersonId() {
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("page", String.valueOf(PAGE_1))
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(Company.class, COMPANY_2, COMPANY_1, COMPANY_3));
    }

    @Test
    void getAllByRegion() {
    }

    @Test
    void getAllByReliability() {
    }

    @Test
    void getAllByType() {
    }

    @Test
    void getAllByTitle() {
    }

    @Test
    void getAllByAddress() {
    }

    @Test
    void getAllByItb() {
    }
}