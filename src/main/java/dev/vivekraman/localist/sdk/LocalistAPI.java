package dev.vivekraman.localist.sdk;

import java.time.ZonedDateTime;

import dev.vivekraman.localist.sdk.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface defining the Localist API operations.
 */
public interface LocalistAPI {
  /**
   * Get a list of events.
   *
   * @return A paged list of events
   */
  Flux<Event> getEvents(ZonedDateTime startDate, ZonedDateTime endDate, int page, int size);

  /**
   * Get details about one event.
   *
   * @param eventId The event ID
   * @return The event details
   */
  Mono<Event> getEvent(Long eventId);
}