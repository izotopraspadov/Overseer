package edu.born.overseer.repository.impl

import edu.born.overseer.data.CONTACT_PERSON_1_ID
import edu.born.overseer.data.CompanyData.COMPANY_1
import edu.born.overseer.data.ContactPersonData.CONTACT_PERSON_1
import edu.born.overseer.data.INVALID_ID
import edu.born.overseer.data.INVALID_PHONE_NUMBER
import edu.born.overseer.data.getPreparedContactPersonCreate
import edu.born.overseer.model.ContactPerson
import edu.born.overseer.model.Email
import edu.born.overseer.model.Phone
import edu.born.overseer.repository.ContactPersonRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.TransactionSystemException
import java.lang.Boolean.FALSE

internal class ContactPersonRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var contactPersonRepository: ContactPersonRepository

    @BeforeEach
    fun setUp() {
        contactPersonRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedContactPersonCreate()
        val savedId = contactPersonRepository.save(prepared, prepared.company.id).id
        val received = contactPersonRepository.getById(savedId)

        assertEquals(received, prepared)
        assertEquals(HashSet<Email>(received.emails), HashSet<Email>(prepared.emails))
        assertEquals(HashSet<Phone>(received.phones), HashSet<Phone>(prepared.phones))
    }

    @Test
    fun createWithInvalidPhoneNumber() {
        val prepared = getPreparedContactPersonCreate()
        prepared.phones.first().apply {
            number = INVALID_PHONE_NUMBER
        }

        assertThrows(TransactionSystemException::class.java) {
            contactPersonRepository.save(prepared, prepared.company.id)
        }
    }

    @Test
    fun update() {
        val prepared = ContactPerson(CONTACT_PERSON_1).apply {
            fullName = "Updated person"
        }
        val updatedId = contactPersonRepository.save(prepared, prepared.company.id).id
        val received = contactPersonRepository.getById(updatedId)

        assertEquals(received, prepared)
        assertEquals(HashSet<Email>(received.emails), HashSet<Email>(prepared.emails))
        assertEquals(HashSet<Phone>(received.phones), HashSet<Phone>(prepared.phones))
    }

    @Test
    fun delete() {
        contactPersonRepository.delete(CONTACT_PERSON_1_ID)

        assertNull(contactPersonRepository.getById(CONTACT_PERSON_1_ID))
    }

    @Test
    fun deleteNotExecute() {
        assertEquals(contactPersonRepository.delete(INVALID_ID), FALSE)
    }

    @Test
    fun getById() {
        assertEquals(contactPersonRepository.getById(CONTACT_PERSON_1_ID), CONTACT_PERSON_1)
    }

    @Test
    fun getByIdWithCompany() {
        val received = contactPersonRepository.getByIdWithCompany(CONTACT_PERSON_1_ID)
        val company = received.company

        assertEquals(received, CONTACT_PERSON_1)
        assertEquals(company, COMPANY_1)
    }
}