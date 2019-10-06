package edu.guap.enclave.repository.jpa;

import edu.guap.enclave.RegionTestData;
import edu.guap.enclave.model.Region;
import edu.guap.enclave.repository.RegionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/POPULATE_DB.sql", config = @SqlConfig(encoding = "UTF-8"))
class RegionRepositoryImplTest {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void save() {
        regionRepository.save(RegionTestData.getCreated());
    }

    @Test
    void delete() {
        regionRepository.delete(RegionTestData.REGION_1_ID);
    }

    @Test
    void get() {
        regionRepository.get(RegionTestData.REGION_1_ID);
    }

    @Test
    void getAll() {
        regionRepository.getAll().forEach(System.out::println);
    }
}