package com.localist.sdk.internal;

import com.localist.sdk.LocalistAPI;
import com.localist.sdk.LocalistAPIException;
import com.localist.sdk.model.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.Optional;

@Component
@EnableConfigurationProperties(LocalistProperties.class)
public class LocalistAPIClient implements LocalistAPI {
  private final WebClient webClient;
  private final LocalistProperties properties;
  private static final String API_VERSION = "2";

  public LocalistAPIClient(WebClient.Builder webClientBuilder, LocalistProperties properties) {
    this.properties = properties;
    this.webClient = webClientBuilder
        .baseUrl(properties.getBaseUrl() + "/api/" + API_VERSION)
        .defaultHeaders(headers -> {
          if (properties.getAccessToken() != null) {
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getAccessToken());
          }
        })
        .build();
  }

  @Override
  public Flux<Organization> getOrganizations() {
    return getOrganizationsWithPage(1, properties.getDefaultPageSize());
  }

  private Flux<Organization> getOrganizationsWithPage(int page, final int requestedPageSize) {
    final int pageSize = validatePageSize(requestedPageSize);
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/organizations")
            .queryParam("page", page)
            .queryParam("pp", pageSize)
            .build())
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(new ParameterizedTypeReference<LocalistResponse<Organization>>() {
        })
        .timeout(Duration.ofMillis(properties.getTimeout()))
        .flatMapMany(response -> Flux.fromIterable(
            Optional.ofNullable(response.getOrganizations()).orElseThrow(
                () -> new LocalistAPIException("No organizations found in response", HttpStatus.NOT_FOUND))));
  }

  @Override
  public Mono<Organization> getOrganization(Long organizationId) {
    return webClient.get()
        .uri("/organizations/{id}", organizationId)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(Organization.class)
        .timeout(Duration.ofMillis(properties.getTimeout()));
  }

  @Override
  public Flux<Event> getEvents() {
    return getEventsWithPage(1, properties.getDefaultPageSize());
  }

  private Flux<Event> getEventsWithPage(int page, final int requestedPageSize) {
    final int pageSize = validatePageSize(requestedPageSize);
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/events")
            .queryParam("page", page)
            .queryParam("pp", pageSize)
            .build())
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(new ParameterizedTypeReference<LocalistResponse<Event>>() {
        })
        .timeout(Duration.ofMillis(properties.getTimeout()))
        .flatMapMany(response -> Flux.fromIterable(
            Optional.ofNullable(response.getEvents())
                .orElseThrow(() -> new LocalistAPIException("No events found in response", HttpStatus.NOT_FOUND))));
  }

  @Override
  public Mono<Event> getEvent(Long eventId) {
    return webClient.get()
        .uri("/events/{id}", eventId)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(Event.class)
        .timeout(Duration.ofMillis(properties.getTimeout()));
  }

  @Override
  public Flux<Community> getCommunities(Long organizationId) {
    return getCommunitiesWithPage(organizationId, 1, properties.getDefaultPageSize());
  }

  private Flux<Community> getCommunitiesWithPage(Long organizationId, int page, final int requestedPageSize) {
    final int pageSize = validatePageSize(requestedPageSize);
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/organizations/{organizationId}/communities")
            .queryParam("page", page)
            .queryParam("pp", pageSize)
            .build(organizationId))
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(new ParameterizedTypeReference<LocalistResponse<Community>>() {
        })
        .timeout(Duration.ofMillis(properties.getTimeout()))
        .flatMapMany(response -> Flux.fromIterable(
            Optional.ofNullable(response.getCommunities()).orElseThrow(
                () -> new LocalistAPIException("No communities found in response", HttpStatus.NOT_FOUND))));
  }

  @Override
  public Mono<Community> getCommunity(Long communityId) {
    return webClient.get()
        .uri("/communities/{id}", communityId)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Client error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
            .map(error -> new LocalistAPIException("Server error: " + error,
                HttpStatus.valueOf(response.statusCode().value()))))
        .bodyToMono(Community.class)
        .timeout(Duration.ofMillis(properties.getTimeout()));
  }

  private int validatePageSize(int pageSize) {
    if (pageSize <= 0) {
      return properties.getDefaultPageSize();
    }
    return Math.min(pageSize, properties.getMaxPageSize());
  }
}