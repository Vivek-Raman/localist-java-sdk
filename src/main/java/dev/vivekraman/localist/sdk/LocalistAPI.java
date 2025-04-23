package dev.vivekraman.localist.sdk;

import dev.vivekraman.localist.sdk.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface defining the Localist API operations.
 * All methods return reactive types (Mono/Flux) for non-blocking operations.
 */
public interface LocalistAPI {
  /**
   * Get a list of organizations on this calendar.
   *
   * @return A paged list of organizations
   */
  Flux<Organization> getOrganizations();

  /**
   * Get details about one organization.
   *
   * @param organizationId The organization ID
   * @return The organization details
   */
  Mono<Organization> getOrganization(Long organizationId);

  /**
   * Get a list of events.
   *
   * @return A paged list of events
   */
  Flux<Event> getEvents();

  /**
   * Get details about one event.
   *
   * @param eventId The event ID
   * @return The event details
   */
  Mono<Event> getEvent(Long eventId);

  /**
   * Get a list of communities.
   *
   * @param organizationId The organization ID
   * @return A paged list of communities
   */
  Flux<Community> getCommunities(Long organizationId);

  /**
   * Get details about one community.
   *
   * @param communityId The community ID
   * @return The community details
   */
  Mono<Community> getCommunity(Long communityId);
}