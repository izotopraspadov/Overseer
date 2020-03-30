package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.ContactPersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static edu.born.overseer.ContactPersonTestData.getPreparedCreate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class ContactPersonRepositoryImplTest {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = contactPersonRepository
                .save(prepared, prepared.getCompany().getId())
                .getId();
        prepared.setId(savedId);
        assertThat(contactPersonRepository.getById(savedId), is(equalTo(prepared)));
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