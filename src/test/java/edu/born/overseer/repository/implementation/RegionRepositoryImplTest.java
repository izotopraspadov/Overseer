package edu.born.overseer.repository.implementation;

import edu.born.overseer.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static edu.born.overseer.data.RegionTestData.*;
import static edu.born.overseer.data.TestDataUtil.INVALID_ID;
import static org.junit.jupiter.api.Assertions.*;

class RegionRepositoryImplTest extends AbstractRepositoryTest {

    @Autowired
    private RegionRepository regionRepository;

    @BeforeEach
    public void setUp() throws Exception {
        regionRepository.evictCache();
    }

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
        regionRepository.delete(REGION_1_ID);
        assertNull(regionRepository.getById(REGION_1_ID));
    }

    @Test
    void deleteNotExecute() {
        assertEquals(regionRepository.delete(INVALID_ID), Boolean.FALSE);
    }

    @Test
    void getById() {
        assertEquals(regionRepository.getById(REGION_1_ID), REGION_1);
    }

  /*  @Test
    void getAll() {
        assertThat(regionRepository.getAll(unlimitedPageLength()), contains(REGION_1,
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
                REGION_13));
    }
*/
}