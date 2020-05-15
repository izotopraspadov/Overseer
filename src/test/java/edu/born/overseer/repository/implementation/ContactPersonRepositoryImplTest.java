package edu.born.overseer.repository.implementation;

import edu.born.overseer.data.PhoneTestData;
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
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static edu.born.overseer.data.CompanyTestData.COMPANY_1;
import static edu.born.overseer.data.CompanyTestData.COMPANY_1_ID;
import static edu.born.overseer.data.ContactPersonTestData.*;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static edu.born.overseer.model.OwnerType.CONTACT_PERSON;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals(received, prepared);
        assertEquals(List.copyOf(received.getEmails()), List.copyOf(prepared.getEmails()));
        assertEquals(List.copyOf(received.getPhones()), List.copyOf(prepared.getPhones()));
    }

    @Test
    void createWithInvalidPhoneNumber() {
        var prepared = getPreparedCreate();
        prepared.setPhones(PhoneTestData.getPreparedCreateInvalidSet(prepared));

        assertThrows(TransactionSystemException.class, () -> contactPersonRepository.save(prepared, prepared.getCompany().getId()));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();

        var updatedId = contactPersonRepository
                .save(prepared, prepared.getCompany().getId())
                .getId();

        var received = contactPersonRepository.getById(updatedId);

        assertEquals(received, prepared);
        assertEquals(Set.copyOf(received.getEmails()), Set.copyOf(prepared.getEmails()));
        assertEquals(Set.copyOf(received.getPhones()), Set.copyOf(prepared.getPhones()));
    }

    @Test
    void delete() {
        assertEquals(contactPersonRepository.delete(CONTACT_PERSON_1_ID), Boolean.TRUE);
        assertThat(contactPersonRepository.getAll(), not(contains(CONTACT_PERSON_1)));
        assertEquals(emailRepository.getAllBySpecificOwner(CONTACT_PERSON_1_ID, CONTACT_PERSON), Collections.EMPTY_LIST);
        assertEquals(phoneRepository.getAllBySpecificOwner(CONTACT_PERSON_1_ID, CONTACT_PERSON), Collections.EMPTY_LIST);
    }

    @Test
    void deleteNotExecute() {
        assertEquals(contactPersonRepository.delete(INVALID_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(contactPersonRepository.getById(CONTACT_PERSON_1_ID), CONTACT_PERSON_1);
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NoResultException.class, () -> contactPersonRepository.getById(INVALID_ID));
    }

    @Test
    void getByIdWithCompany() {
        var received = contactPersonRepository.getByIdWithCompany(CONTACT_PERSON_1_ID);
        var company = received.getCompany();

        assertEquals(received, CONTACT_PERSON_1);
        assertEquals(company, COMPANY_1);
    }

    @Test
    void getAll() {
        assertThat(contactPersonRepository.getAll(), contains(CONTACT_PERSON_8,
                CONTACT_PERSON_6,
                CONTACT_PERSON_4,
                CONTACT_PERSON_1,
                CONTACT_PERSON_5,
                CONTACT_PERSON_3,
                CONTACT_PERSON_2,
                CONTACT_PERSON_7)
        );
    }

    @Test
    void getAllByCompany() {
        assertThat(contactPersonRepository.getAllByCompany(COMPANY_1_ID), contains(CONTACT_PERSON_1,
                CONTACT_PERSON_3,
                CONTACT_PERSON_2)
        );
    }

}