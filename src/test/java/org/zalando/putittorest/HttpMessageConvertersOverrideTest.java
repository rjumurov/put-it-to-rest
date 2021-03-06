package org.zalando.putittorest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
@Component
public final class HttpMessageConvertersOverrideTest {

    @Configuration
    @Import(DefaultTestConfiguration.class)
    public static class TestConfiguration {

        @Bean
        @Qualifier("example")
        public HttpMessageConverters exampleHttpMessageConverters() {
            return new HttpMessageConverters(false, singleton(new Jaxb2RootElementHttpMessageConverter()));
        }

    }

    @Autowired
    @Qualifier("example")
    private HttpMessageConverters unit;

    @Test
    public void shouldOverride() {
        assertThat(unit, contains(instanceOf(Jaxb2RootElementHttpMessageConverter.class)));
    }

}
