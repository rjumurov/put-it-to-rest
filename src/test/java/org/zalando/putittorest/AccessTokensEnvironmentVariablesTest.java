package org.zalando.putittorest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.test.ImportAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
public final class AccessTokensEnvironmentVariablesTest {

    @ClassRule
    public static final EnvironmentVariables ENVIRONMENT = new EnvironmentVariables();

    @Configuration
    @ImportAutoConfiguration(RestClientAutoConfiguration.class)
    public static class TestConfiguration {

        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

    }

    @BeforeClass
    public static void setAccessTokenUrl() {
        ENVIRONMENT.set("ACCESS_TOKEN_URL", "http://example.com");
    }

    @AfterClass
    public static void removeAccessTokenUrl() {
        ENVIRONMENT.set("ACCESS_TOKEN_URL", null);
    }

    @Test
    public void shouldRun() {
        // if the application context is booting up, I'm happy
    }

}
