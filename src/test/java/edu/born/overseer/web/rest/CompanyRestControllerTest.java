package edu.born.overseer.web.rest;

import edu.born.overseer.model.Company;
import edu.born.overseer.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static edu.born.overseer.TestUtil.*;
import static edu.born.overseer.data.CompanyTestData.*;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_1;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_5;
import static edu.born.overseer.data.RegionTestData.REGION_1_ID;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CompanyRestControllerTest extends AbstractControllerTest {

    private static String REST_URL = CompanyRestController.REST_URL + '/';

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() throws Exception {
        companyRepository.evictCache();
    }

    @Test
    void delete_() throws Exception {
        mockMvc.perform(delete(REST_URL + COMPANY_1_ID)
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isNoContent());

        assertNull(companyRepository.getById(COMPANY_1_ID));
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get(REST_URL + COMPANY_1_ID)
                .with(userHttpBasic(EMPLOYEE_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(Company.class, COMPANY_1));
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
    void getAllByRegion() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("page", String.valueOf(PAGE_1))
                .param("region_id", String.valueOf(REGION_1_ID))
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(Company.class, new Company[]{COMPANY_1}));
    }

}