package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.ContactPersonRepository;
import edu.born.overseer.repository.EmailRepository;
import edu.born.overseer.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static edu.born.overseer.ContactPersonTestData.*;
import static edu.born.overseer.model.TypeOwner.CONTACT_PERSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class ContactPersonRepositoryImplTest {

    @Autowired
    private ContactPersonRepository contactPersonRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();

        var savedId = contactPersonRepository
                .save(prepared, prepared.getCompany().getId())
                .getId();

        var received = contactPersonRepository.getById(savedId);

        assertThat(received, is(equalTo(prepared)));
        assertThat(List.copyOf(received.getEmails()), is(equalTo(List.copyOf(prepared.getEmails()))));
        assertThat(List.copyOf(received.getPhones()), is(equalTo(List.copyOf(prepared.getPhones()))));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();
        var updatedId = contactPersonRepository
                .save(prepared, prepared.getCompany().getId())
                .getId();

        var received = contactPersonRepository.getById(updatedId);

        assertThat(received, is(equalTo(prepared)));
        assertThat(List.copyOf(received.getEmails()), is(equalTo(List.copyOf(prepared.getEmails()))));
        assertThat(List.copyOf(received.getPhones()), is(equalTo(List.copyOf(prepared.getPhones()))));
    }

    @Test
    void delete() {
        assertThat(contactPersonRepository.delete(CONTACT_PERSON_1_ID), is(equalTo(Boolean.TRUE)));
        assertThat(contactPersonRepository.getAll(), not(contains(CONTACT_PERSON_1)));
        assertThat(emailRepository.getAllBySpecificOwner(CONTACT_PERSON_1_ID, CONTACT_PERSON), is(equalTo(Collections.EMPTY_LIST)));
        assertThat(phoneRepository.getAllBySpecificOwner(CONTACT_PERSON_1_ID, CONTACT_PERSON), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    void deleteNotExecute() {
        assertThat(contactPersonRepository.delete(INVALID_CONTACT_PERSON_ID), is(equalTo(Boolean.FALSE)));
    }

    @Test
    void getById() {
    }

    @Test
    void getByIdNotFound() {
    }

    @Test
    void getWithCompany() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByCompany() {
    }

}