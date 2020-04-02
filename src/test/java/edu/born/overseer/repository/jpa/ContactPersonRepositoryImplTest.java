package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.ContactPersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static edu.born.overseer.ContactPersonTestData.*;
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
    }

    @Test
    void deleteNotExecute() {
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