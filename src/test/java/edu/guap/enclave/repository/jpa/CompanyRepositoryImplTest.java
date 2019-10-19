package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/POPULATE_DB.sql", config = @SqlConfig(encoding = "UTF-8"))
class CompanyRepositoryImplTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
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
    void findByItb() {
    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByTitle() {
    }
}