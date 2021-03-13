package edu.born.overseer.repository.impl

import edu.born.overseer.data.*
import edu.born.overseer.model.Region
import edu.born.overseer.repository.RegionRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import java.lang.Boolean.FALSE

internal class RegionRepositoryImplTest : AbstractRepositoryTest() {

    @Autowired
    private lateinit var regionRepository: RegionRepository

    @BeforeEach
    fun setUp() {
        regionRepository.evictCache()
    }

    @Test
    fun create() {
        val prepared = getPreparedRegionCreate()
        val savedId = regionRepository.save(prepared).id
        prepared.id = savedId

        assertEquals(regionRepository.getById(savedId), prepared)
    }

    @Test
    fun createDuplicate() {
        val duplicate = getPreparedRegionCreate().apply {
            title = "Moscow"
        }

        assertThrows(DataIntegrityViolationException::class.java) {
            regionRepository.save(duplicate)
        }
    }

    @Test
    fun update() {
        val prepared = Region(REGION_1).apply {
            title = "Updated title"
        }
        val updated = regionRepository.save(prepared)

        assertEquals(updated, prepared)
    }

    @Test
    fun delete() {
        regionRepository.delete(REGION_1_ID)

        assertNull(regionRepository.getById(REGION_1_ID))
    }

    @Test
    fun deleteNotExecute() {
        assertEquals(regionRepository.delete(INVALID_ID), FALSE)
    }

    @Test
    fun getById() {
        assertEquals(regionRepository.getById(REGION_1_ID), REGION_1)
    }

    @Test
    fun getAll() {
        assertThat(regionRepository.getAll(1, "Mo"), contains(REGION_2))
    }
}