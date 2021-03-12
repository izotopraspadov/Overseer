package edu.born.overseer.repository.implementation

import edu.born.overseer.data.*
import edu.born.overseer.data.CompanyData.COMPANY_1
import edu.born.overseer.model.Company
import edu.born.overseer.repository.CompanyRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.transaction.TransactionSystemException
import java.lang.Boolean.FALSE

internal class CompanyRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @BeforeEach
    fun setUp() {
        companyRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedCompanyCreate()
        val savedId = companyRepository.save(prepared, prepared.region.id).id
        prepared.id = savedId

        assertEquals(companyRepository.getById(savedId), prepared)
    }

    @Test
    fun createDuplicate() {
        val prepared = getPreparedCompanyCreate().apply {
            itn = COMPANY_1.itn // duplicate
        }

        assertThrows(DataIntegrityViolationException::class.java) {
            companyRepository.save(prepared, prepared.region.id)
        }
    }

    @Test
    fun createWithInvalidITN() {
        val prepared = getPreparedCompanyCreate().apply {
            itn = "001" // invalid itn
        }

        assertThrows(TransactionSystemException::class.java) {
            companyRepository.save(prepared, prepared.region.id)
        }
    }

    @Test
    fun update() {
        val prepared = Company(COMPANY_1).apply {
            title = "Updated Company" // updated
        }
        val updated = companyRepository.save(prepared, prepared.region.id)

        assertEquals(updated, prepared)
    }

    @Test
    fun delete() {
        companyRepository.delete(COMPANY_1_ID)

        assertNull(companyRepository.getById(COMPANY_1_ID))
    }

    @Test
    fun deleteNotExecute() {
        assertEquals(companyRepository.delete(INVALID_ID), FALSE)
    }

    @Test
    fun getAll() {
        // by page, contactPersonId, regionId
        val companies = companyRepository.getAll(
                1, CONTACT_PERSON_1_ID, REGION_1_ID, null, null, null, "No st", null)

        assertThat(companies, contains(COMPANY_1))
    }
}