package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;

import static edu.born.overseer.CompanyTestData.*;
import static edu.born.overseer.ContactPersonTestData.CONTACT_PERSON_1_ID;
import static edu.born.overseer.RegionTestData.REGION_1_ID;
import static edu.born.overseer.model.Reliability.LOW;
import static edu.born.overseer.model.TypeCompany.OUR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class CompanyRepositoryImplTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = companyRepository.save(prepared).getId();
        prepared.setId(savedId);

        assertEquals(companyRepository.getById(savedId), prepared);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataIntegrityViolationException.class, () -> companyRepository.save(getPreparedDuplicate()));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();
        var updated = companyRepository.save(prepared);

        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        assertEquals(companyRepository.delete(COMPANY_1_ID), Boolean.TRUE);
        assertThat(companyRepository.getAll(), not(contains(COMPANY_1)));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(companyRepository.delete(INVALID_COMPANY_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(companyRepository.getById(COMPANY_1_ID), COMPANY_1);
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NoResultException.class, () -> companyRepository.getById(INVALID_COMPANY_ID));
    }

    @Test
    void getByItb() {
        assertThat(companyRepository.getAllByItb(COMPANY_1.getItn()), contains(COMPANY_1));
    }

    @Test
    void getByItbPartialMatch() {
        assertThat(companyRepository.getAllByItb("00000000"), contains(
                COMPANY_2,
                COMPANY_1,
                COMPANY_3)
        );
    }

    @Test
    void getByContactPersonId() {
        assertEquals(companyRepository.getByContactPersonId(CONTACT_PERSON_1_ID), COMPANY_1);
    }

    @Test
    void getAll() {
        assertThat(companyRepository.getAll(), contains(
                COMPANY_2,
                COMPANY_1,
                COMPANY_3)
        );
    }

    @Test
    void getAllByRegion() {
        assertThat(companyRepository.getAllByRegion(REGION_1_ID), contains(COMPANY_1));
    }

    @Test
    void getAllByReliability() {
        assertThat(companyRepository.getAllByReliability(LOW), contains(COMPANY_1));
    }

    @Test
    void getAllByType() {
        assertThat(companyRepository.getAllByType(OUR), contains(COMPANY_1));
    }

    @Test
    void getAllByAddress() {
        assertThat(companyRepository.getAllByAddress(COMPANY_1.getAddress()), contains(COMPANY_1));
    }

    @Test
    void getAllByAddressPartialMatch() {
        assertThat(companyRepository.getAllByAddress("Н"), contains(
                COMPANY_2,
                COMPANY_1)
        );
    }

    @Test
    void getAllByTitle() {
        assertThat(companyRepository.getAllByTitle(COMPANY_1.getTitle()), contains(COMPANY_1));
    }

    @Test
    void getAllByTitlePartialMatch() {
        assertThat(companyRepository.getAllByTitle("Пер"), contains(COMPANY_1));
    }

}