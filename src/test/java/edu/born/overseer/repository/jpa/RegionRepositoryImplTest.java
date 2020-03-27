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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNot.not;


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
        Assert.assertEquals(regionRepository.delete(REGION_1_ID), Boolean.TRUE);
        assertThat(regionRepository.getAll(), not(hasItem(REGION_1)));
    }

    @Test
    void deleteNotExecute() {
        Assert.assertEquals(regionRepository.delete(INVALID_REGION_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        Assert.assertEquals(regionRepository.getById(REGION_1_ID), REGION_1);
    }

    @Test()
    void getByIdNotFound() {
        Assertions.assertThrows(NoResultException.class, () -> regionRepository.getById(INVALID_REGION_ID));
    }

    @Test
    void getAll() {
        assertThat(regionRepository.getAll(), hasItems(REGION_1,
                REGION_2,
                REGION_3,
                REGION_3,
                REGION_4,
                REGION_5,
                REGION_6,
                REGION_7,
                REGION_8,
                REGION_9,
                REGION_10,
                REGION_11,
                REGION_12,
                REGION_13)
        );
    }
}