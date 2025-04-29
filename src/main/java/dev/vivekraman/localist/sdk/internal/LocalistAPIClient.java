package dev.vivekraman.localist.sdk.internal;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import dev.vivekraman.localist.sdk.LocalistAPI;
import dev.vivekraman.localist.sdk.LocalistAPIException;
import dev.vivekraman.localist.sdk.model.*;
import dev.vivekraman.localist.sdk.model.LocalistEventsResponse.EventHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
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
  public Flux<Event> getEvents(ZonedDateTime startDate, ZonedDateTime endDate, int page, int size) {
    final int pageSize = validatePageSize(page);
    return webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/events")
            .queryParam("start", startDate)
            .queryParam("end", endDate)
            .queryParam("page", page)
            .queryParam("pp", pageSize)
            .build())
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, this::handleClientError)
        .onStatus(HttpStatusCode::is5xxServerError, this::handleServerError)
        .bodyToMono(new ParameterizedTypeReference<LocalistEventsResponse>() {
        })
        .timeout(Duration.ofMillis(properties.getTimeout()))
        .flatMapMany(response -> Flux.fromIterable(
            Optional.ofNullable(response.getEvents())
                .map(eventHolders -> eventHolders.stream().map(EventHolder::getEvent).toList())
                .orElseThrow(() -> new LocalistAPIException("No events found in response", HttpStatus.NOT_FOUND))));

  }

  @Override
  public Mono<Event> getEvent(Long eventId) {
    return webClient.get()
        .uri("/events/{id}", eventId)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, this::handleClientError)
        .onStatus(HttpStatusCode::is5xxServerError, this::handleServerError)
        .bodyToMono(Event.class)
        .timeout(Duration.ofMillis(properties.getTimeout()));
  }

  private int validatePageSize(int pageSize) {
    if (pageSize <= 0) {
      return properties.getDefaultPageSize();
    }
    return Math.min(pageSize, properties.getMaxPageSize());
  }

  private Mono<? extends Throwable> handleClientError(ClientResponse response) {
    return response.bodyToMono(String.class)
        .map(error -> new LocalistAPIException(
            "Client error: " + error,
            HttpStatus.valueOf(response.statusCode().value())));
  }

  private Mono<? extends Throwable> handleServerError(ClientResponse response) {
    return response.bodyToMono(String.class)
        .map(error -> new LocalistAPIException(
            "Server error: " + error,
            HttpStatus.valueOf(response.statusCode().value())));
  }
}