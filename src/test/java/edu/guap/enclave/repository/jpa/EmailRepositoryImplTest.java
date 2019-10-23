package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.model.TypeOwner;
import edu.guap.enclave.repository.EmailRepository;
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
class EmailRepositoryImplTest {

    @Autowired
    private EmailRepository emailRepository;

    @Test
    void getAllBySpecificOwner() {
        emailRepository.getAllBySpecificOwner(100026, TypeOwner.EMPLOYEE)
                .forEach(System.out::println);
    }
}