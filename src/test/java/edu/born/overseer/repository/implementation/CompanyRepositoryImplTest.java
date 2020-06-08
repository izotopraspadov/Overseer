package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.transaction.TransactionSystemException;

import static edu.born.overseer.TestUtil.unlimitedPageLength;
import static edu.born.overseer.data.CompanyTestData.*;
import static edu.born.overseer.data.ContactPersonTestData.CONTACT_PERSON_1_ID;
import static edu.born.overseer.data.RegionTestData.REGION_1_ID;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static edu.born.overseer.model.CompanyType.OUR;
import static edu.born.overseer.model.ReliabilityType.LOW;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() throws Exception {
        companyRepository.evictCache();
    }

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = companyRepository.save(prepared, prepared.getRegion().getId()).getId();
        prepared.setId(savedId);

        assertEquals(companyRepository.getById(savedId), prepared);
    }

    @Test
    void createDuplicate() {
        var prepared = getPreparedDuplicate();
        assertThrows(DataIntegrityViolationException.class, () -> companyRepository.save(prepared, prepared.getRegion().getId()));
    }

    @Test
    void createWithInvalidITN() {
        var prepared = getPreparedCreateWithInvalidITN();

        assertThrows(TransactionSystemException.class, () -> companyRepository.save(prepared, prepared.getRegion().getId()));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();

        var updated = companyRepository.save(prepared, prepared.getRegion().getId());

        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        companyRepository.delete(COMPANY_1_ID);
        assertNull(companyRepository.getById(COMPANY_1_ID));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(companyRepository.delete(INVALID_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(companyRepository.getById(COMPANY_1_ID), COMPANY_1);
    }

    @Test
    void getByContactPersonId() {
        assertEquals(companyRepository.getByContactPersonId(CONTACT_PERSON_1_ID), COMPANY_1);
    }

    @Test
    void getByContactPersonIdNotFound() {
        assertThrows(NotFoundException.class, () -> companyRepository.getByContactPersonId(INVALID_ID));
    }

    @Test
    void getAll() {
        assertThat(companyRepository.getAll(unlimitedPageLength()), contains(
                COMPANY_2,
                COMPANY_1,
                COMPANY_3)
        );
    }

    @Test
    void getAllByRegion() {
        assertThat(companyRepository.getAllByRegion(REGION_1_ID, unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByReliability() {
        assertThat(companyRepository.getAllByReliability(LOW, unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByType() {
        assertThat(companyRepository.getAllByType(OUR, unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByTitle() {
        assertThat(companyRepository.getAllByTitle(COMPANY_1.getTitle(), unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByTitlePartialMatch() {
        assertThat(companyRepository.getAllByTitle("Пер", unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByAddress() {
        assertThat(companyRepository.getAllByAddress(COMPANY_1.getAddress(), unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getAllByAddressPartialMatch() {
        assertThat(companyRepository.getAllByAddress("Н", unlimitedPageLength()), contains(
                COMPANY_2,
                COMPANY_1,
                COMPANY_3)
        );
    }

    @Test
    void getByItn() {
        assertThat(companyRepository.getAllByItn(COMPANY_1.getItn(), unlimitedPageLength()), contains(COMPANY_1));
    }

    @Test
    void getByItbPartialMatch() {
        assertThat(companyRepository.getAllByItn("00000000", unlimitedPageLength()), contains(
                COMPANY_2,
                COMPANY_1,
                COMPANY_3)
        );
    }

}