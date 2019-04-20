package com.objectpartners.plummer.spockctx.springbean

import com.objectpartners.plummer.spockctx.Application
import com.objectpartners.plummer.spockctx.services.DataByIdService
import com.objectpartners.plummer.spockctx.services.DataListService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

class SpringBeanSpec {
    @AutoConfigureMockMvc
    @SpringBootTest(classes = [Application])
    abstract static class BaseSpec extends Specification {
        @Autowired
        MockMvc mockMvc
    }

    static class GetAll extends BaseSpec {
        @SpringBean
        DataListService service = Mock()

        def 'get'() {
            when:
            ResultActions result = this.mockMvc.perform(get("/data"))

            then:
            1 * service.get() >> ['1', '2', '3']
            0 * _
            result.andExpect(content().string('["1","2","3"]'))
        }
    }

    static class GetById extends BaseSpec {
        @SpringBean
        DataByIdService service = Mock()

        def 'getById'() {
            when:
            ResultActions result = this.mockMvc.perform(get("/data/1"))

            then:
            1 * service.get('1') >> '123'
            0 * _
            result.andExpect(content().string('123'))
        }
    }
}
