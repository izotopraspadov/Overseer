package edu.born.overseer.repository.jpa;

import edu.born.overseer.repository.RegionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;

import static edu.born.overseer.RegionTestData.*;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class RegionRepositoryImplTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void getById() {
        Assert.assertEquals(regionRepository.getById(REGION_1_ID), REGION_1);
    }

    @Test()
    void getByIdNotFound() {
        Assertions.assertThrows(NoResultException.class, () -> regionRepository.getById(1));
    }

    @Test
    void getAll() {
    }
}