package edu.born.overseer.web.rest;

import edu.born.overseer.model.ContactPerson;
import edu.born.overseer.repository.ContactPersonRepository;
import edu.born.overseer.web.json.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static edu.born.overseer.TestUtil.*;
import static edu.born.overseer.data.CompanyTestData.COMPANY_1_ID;
import static edu.born.overseer.data.ContactPersonTestData.*;
import static edu.born.overseer.data.EmployeeTestData.EMPLOYEE_5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ContactPersonRestControllerTest extends AbstractControllerTest {

    private static String REST_URL = ContactPersonRestController.REST_URL + '/';

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @BeforeEach
    public void setUp() throws Exception {
        contactPersonRepository.evictCache();
    }

    @Test
    void create() throws Exception {
        var prepared = getPreparedCreate();
        ResultActions action = mockMvc.perform(post(REST_URL, COMPANY_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(prepared))
                .with(userHttpBasic(EMPLOYEE_5)));
        var received = readFromJsonResultActions(action, ContactPerson.class);
        prepared.setId(received.getId());

        assertEquals(received, prepared);
    }

    @Test
    void getAllByCompany() throws Exception  {
        mockMvc.perform(get(REST_URL, COMPANY_1_ID)
                .param("page", String.valueOf(PAGE_1))
                .with(userHttpBasic(EMPLOYEE_5)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(ContactPerson.class, CONTACT_PERSON_1, CONTACT_PERSON_3, CONTACT_PERSON_2));
    }
}