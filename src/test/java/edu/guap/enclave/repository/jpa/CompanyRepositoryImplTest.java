package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.Reliability;
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
        companyRepository.getAllByTitle("%торая компания%").forEach(System.out::println);
    }

    @Test
    void getAllByRegion() {
        companyRepository.getAllByRegion(100001).forEach(System.out::println);
    }

    @Test
    void getAllByReliability() {
        companyRepository.getAllByReliability(Reliability.MIDDLE).forEach(System.out::println);
    }

    @Test
    void getAllByType() {
    }

    @Test
    void findByItb() {
        System.out.println(companyRepository.findByItb("000000000000"));
    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByContactPerson() {
        //companyRepository.getAllByContactPerson(100016).forEach(System.out::println);
    }

}