package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static edu.born.overseer.CompanyTestData.COMPANY_1;
import static edu.born.overseer.CompanyTestData.COMPANY_1_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class CompanyRepositoryImplTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void create() {
    }

    @Test
    void createDuplicate() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteNotExecute() {
    }

    @Test
    void getById() {
        assertThat(companyRepository.getById(COMPANY_1_ID), is(equalTo(COMPANY_1)));
    }

    @Test
    void getByItb() {
    }

    @Test
    void getByContactPerson() {
    }

    @Test()
    void getByIdNotFound() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByRegion() {
    }

    @Test
    void getAllByReliability() {
    }

    @Test
    void getAllByType() {
    }

    @Test
    void getAllByAddress() {
    }

}