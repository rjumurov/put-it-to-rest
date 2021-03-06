package org.zalando.putittorest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.zalando.riptide.AsyncRest;
import org.zalando.riptide.Rest;
import org.zalando.stups.oauth2.spring.client.StupsOAuth2RestTemplate;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DefaultTestConfiguration.class)
@TestPropertySource(properties = {
        "rest.clients.example.timeouts.connect: 2",
        "rest.clients.example.timeouts.read: 3",
})
@Component
public final class ClientConfigurationTest {

    @Autowired
    @Qualifier("example")
    private Rest exampleRest;

    @Autowired
    @Qualifier("example")
    private RestTemplate exampleRestTemplate;

    @Autowired
    @Qualifier("example")
    private AsyncRest exampleAsync;

    @Autowired
    @Qualifier("example")
    private AsyncRest exampleAsyncRestTemplate;

    @Autowired
    @Qualifier("ecb")
    private Rest ecbRest;

    @Autowired
    @Qualifier("ecb")
    private RestTemplate ecbRestTemplate;

    @Autowired
    @Qualifier("ecb")
    private AsyncRest ecbAsync;

    @Autowired
    @Qualifier("ecb")
    private AsyncRest ecbAsyncRestTemplate;

    @Test
    public void shouldWireOAuthCorrectly() {
        assertThat(exampleRest, is(notNullValue()));
        assertThat(exampleRestTemplate, is(instanceOf(StupsOAuth2RestTemplate.class)));
        assertThat(exampleAsync, is(notNullValue()));
        assertThat(exampleAsyncRestTemplate, is(notNullValue()));
    }

    @Test
    public void shouldWireNonOAuthCorrectly() {
        assertThat(ecbRest, is(notNullValue()));
        assertThat(ecbRestTemplate, is(instanceOf(RestTemplate.class)));
        assertThat(ecbRestTemplate, is(not(instanceOf(StupsOAuth2RestTemplate.class))));
        assertThat(ecbAsync, is(notNullValue()));
        assertThat(ecbAsyncRestTemplate, is(notNullValue()));

    }

}
