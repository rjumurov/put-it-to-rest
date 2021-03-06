package org.zalando.putittorest;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.zalando.putittorest.Mocks.isMock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration
@Component
public final class HttpAsyncClientOverrideTest {

    @Configuration
    @Import(DefaultTestConfiguration.class)
    public static class TestConfiguration {

        @Bean
        @Qualifier("example")
        public CloseableHttpAsyncClient exampleHttpAsyncClient() { // TODO document
            return mock(CloseableHttpAsyncClient.class);
        }

    }

    @Autowired
    @Qualifier("example")
    private HttpAsyncClient unit;

    @Test
    public void shouldOverride() {
        assertThat(unit, isMock());
    }

}
