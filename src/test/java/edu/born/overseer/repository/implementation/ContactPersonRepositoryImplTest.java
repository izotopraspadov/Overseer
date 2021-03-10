package edu.born.overseer.repository.implementation;

import edu.born.overseer.data.PhoneTestData;
import edu.born.overseer.repository.ContactPersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Set;

import static edu.born.overseer.data.CompanyTestData.COMPANY_1;
import static edu.born.overseer.data.ContactPersonTestData.*;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static org.junit.jupiter.api.Assertions.*;

class ContactPersonRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @BeforeEach
    public void setUp() throws Exception {
        contactPersonRepository.evictCache();
    }

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
        contactPersonRepository.delete(CONTACT_PERSON_1_ID);
        assertNull(contactPersonRepository.getById(CONTACT_PERSON_1_ID));
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
    void getByIdWithCompany() {
        var received = contactPersonRepository.getByIdWithCompany(CONTACT_PERSON_1_ID);
        var company = received.getCompany();

        assertEquals(received, CONTACT_PERSON_1);
        assertEquals(company, COMPANY_1);
    }
/*
    @Test
    void getAll() {
        assertThat(contactPersonRepository.getAll(unlimitedPageLength()), contains(CONTACT_PERSON_8,
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
        assertThat(contactPersonRepository.getAllByCompany(COMPANY_1_ID, unlimitedPageLength()), contains(CONTACT_PERSON_1, CONTACT_PERSON_3, CONTACT_PERSON_2));
    }*/

}