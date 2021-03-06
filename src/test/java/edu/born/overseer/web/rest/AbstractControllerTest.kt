package edu.born.overseer.web.rest

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlConfig
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import javax.annotation.PostConstruct

@SpringJUnitWebConfig(locations = ["classpath:spring/spring-mvc.xml", "classpath:spring/spring-db.xml"])
@RunWith(SpringJUnit4ClassRunner::class)
@Sql(scripts = ["classpath:db/population.sql"], config = SqlConfig(encoding = "UTF-8"))
abstract class AbstractControllerTest {

    companion object {
        private val CHARACTER_ENCODING_FILTER: CharacterEncodingFilter =
                CharacterEncodingFilter().apply {
                    encoding = "UTF-8"
                    setForceEncoding(true)
                }
    }

    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var webApplicationContext: WebApplicationContext

    @PostConstruct
    private fun postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter<DefaultMockMvcBuilder>(CHARACTER_ENCODING_FILTER)
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
                .build()
    }
}