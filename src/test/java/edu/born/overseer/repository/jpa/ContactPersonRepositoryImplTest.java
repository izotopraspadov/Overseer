package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.ContactPersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class ContactPersonRepositoryImplTest {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Test
    void create() {
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
    }

    @Test
    void getByIdNotFound() {
    }

    @Test
    void getWithCompany() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByCompany() {
    }
}