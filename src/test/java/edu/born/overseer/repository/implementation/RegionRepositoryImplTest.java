package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;

import static edu.born.overseer.RegionTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(locations = {"classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/population.sql", config = @SqlConfig(encoding = "UTF-8"))
class RegionRepositoryImplTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void create() {
        var prepared = getPreparedCreate();
        var savedId = regionRepository.save(prepared).getId();
        prepared.setId(savedId);

        assertEquals(regionRepository.getById(savedId), prepared);
    }

    @Test
    void createDuplicate() {
        assertThrows(DataIntegrityViolationException.class, () -> regionRepository.save(getPreparedDuplicate()));
    }

    @Test
    void update() {
        var prepared = getPreparedUpdate();
        var updated = regionRepository.save(prepared);

        assertEquals(updated, prepared);
    }

    @Test
    void delete() {
        assertEquals(regionRepository.delete(REGION_1_ID), Boolean.TRUE);
        assertThat(regionRepository.getAll(), not(contains(REGION_1)));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(regionRepository.delete(INVALID_REGION_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(regionRepository.getById(REGION_1_ID), REGION_1);
    }

    @Test()
    void getByIdNotFound() {
        assertThrows(NoResultException.class, () -> regionRepository.getById(INVALID_REGION_ID));
    }

    @Test
    void getAll() {
        assertThat(regionRepository.getAll(), contains(REGION_1,
                REGION_2,
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