package com.objectpartners.plummer.spockctx.targetedctx

import com.objectpartners.plummer.spockctx.services.DataByIdService
import com.objectpartners.plummer.spockctx.services.DataListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class TargetedContextSpec {
    @EnableWebMvc
    @AutoConfigureMockMvc
    @SpringBootTest(classes = Config)
    abstract static class BaseSpec extends Specification {
        @Autowired
        MockMvc mockMvc

        protected final static DetachedMockFactory factory = new DetachedMockFactory()

        @Configuration
        @ComponentScan([
                "com.objectpartners.plummer.spockctx.controllers",
                "com.objectpartners.plummer.spockctx.services",
        ])
        static class Config {
        }
    }

    @ContextConfiguration(classes = [GetAllConfig])
    static class GetAll extends BaseSpec {
        @Autowired
        DataListService service

        def 'get'() {
            when:
            ResultActions result = this.mockMvc.perform(get("/data"))

            then:
            1 * service.get() >> ['1', '2', '3']
            0 * _
            result.andExpect(content().string('["1","2","3"]'))
        }

        @TestConfiguration
        static class GetAllConfig {
            @Bean
            @Primary
            DataListService mockDataService() {
                factory.Mock(DataListService)
            }
        }
    }

    @ContextConfiguration(classes = [GetByIdConfig])
    static class GetById extends BaseSpec {
        @Autowired
        DataByIdService service

        def 'getById'() {
            when:
            ResultActions result = this.mockMvc.perform(get("/data/1"))

            then:
            1 * service.get('1') >> '123'
            0 * _
            result.andExpect(content().string('123'))
        }

        @TestConfiguration
        static class GetByIdConfig {
            @Bean
            @Primary
            DataByIdService mockDataService() {
                factory.Mock(DataByIdService)
            }
        }
    }
}
