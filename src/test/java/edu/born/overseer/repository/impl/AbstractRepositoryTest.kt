package edu.born.overseer.repository.impl

import org.junit.runner.RunWith
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@SpringJUnitConfig(locations = ["classpath:spring/spring-db.xml"])
@RunWith(SpringJUnit4ClassRunner::class)
@Sql(scripts = ["classpath:db/population.sql"], config = SqlConfig(encoding = "UTF-8"))
abstract class AbstractRepositoryTest