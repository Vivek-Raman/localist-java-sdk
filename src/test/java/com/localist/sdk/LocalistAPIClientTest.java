package com.localist.sdk;

import com.localist.sdk.internal.LocalistAPIClient;
import com.localist.sdk.internal.LocalistProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

class LocalistAPIClientTest {
  private LocalistAPIClient client;
  private LocalistProperties properties;

  @BeforeEach
  void setUp() {
    properties = new LocalistProperties();
    properties.setBaseUrl("https://demo.localist.com");
    properties.setAccessToken("your-access-token"); // Optional
    properties.setDefaultPageSize(10);
    properties.setMaxPageSize(100);
    properties.setTimeout(30000L);

    client = new LocalistAPIClient(WebClient.builder(), properties);
  }

  @Test
  void getOrganizationsTest() {
    StepVerifier.create(client.getOrganizations())
        .expectNextCount(0) // Expecting empty since this is a demo URL
        .verifyComplete();
  }

  @Test
  void getEventsTest() {
    StepVerifier.create(client.getEvents())
        .expectNextCount(0) // Expecting empty since this is a demo URL
        .verifyComplete();
  }
}