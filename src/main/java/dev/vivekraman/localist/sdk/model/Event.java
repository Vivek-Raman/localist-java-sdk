package dev.vivekraman.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
  private Long id;
  private String title;
  private String description;
  @JsonProperty("description_text")
  private String descriptionText;
  private String status;
  private String experience;
  private String url;
  @JsonProperty("localist_url")
  private String localistUrl;
  @JsonProperty("localist_ics_url")
  private String localistIcsUrl;
  private GeoInfo geo;

  @JsonProperty("created_by")
  private Long createdBy;
  @JsonProperty("updated_by")
  private Long updatedBy;
  @JsonProperty("created_at")
  private ZonedDateTime createdAt;
  @JsonProperty("updated_at")
  private ZonedDateTime updatedAt;

  @JsonProperty("first_date")
  private LocalDate firstDate;
  @JsonProperty("last_date")
  private LocalDate lastDate;

  private String hashtag;
  private String urlname;
  @JsonProperty("user_id")
  private Long userId;
  private String directions;
  @JsonProperty("allows_reviews")
  private boolean allowsReviews;
  @JsonProperty("allows_attendance")
  private boolean allowsAttendance;
  private String location;
  @JsonProperty("room_number")
  private String roomNumber;
  @JsonProperty("location_name")
  private String locationName;
  @JsonProperty("stream_url")
  private String streamUrl;
  @JsonProperty("stream_info")
  private String streamInfo;
  @JsonProperty("stream_embed_code")
  private String streamEmbedCode;

  @JsonProperty("conference_event_id")
  private Long conferenceEventId;
  private String kind;
  @JsonProperty("city_id")
  private Long cityId;
  @JsonProperty("neighborhood_id")
  private Long neighborhoodId;
  @JsonProperty("school_id")
  private Long schoolId;
  @JsonProperty("campus_id")
  private Long campusId;

  private boolean recurring;
  private boolean free;
  @JsonProperty("private")
  private boolean isPrivate;
  private boolean verified;
  private boolean rejected;
  private boolean sponsored;
  private boolean featured;

  @JsonProperty("venue_id")
  private Long venueId;
  @JsonProperty("ticket_url")
  private String ticketUrl;
  @JsonProperty("ticket_cost")
  private String ticketCost;
  @JsonProperty("has_register")
  private boolean hasRegister;

  @JsonProperty("photo_id")
  private Long photoId;
  @JsonProperty("photo_url")
  private String photoUrl;
  @JsonProperty("venue_url")
  private String venueUrl;
  @JsonProperty("detail_views")
  private Integer detailViews;

  private List<String> keywords;
  private List<String> tags;
  private String address;

  @JsonProperty("facebook_id")
  private String facebookId;

  @JsonProperty("event_instances")
  private List<EventInstance> eventInstances;
  private Map<String, Object> filters;
  @JsonProperty("custom_fields")
  private Map<String, Object> customFields;
  private List<Department> departments;

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class EventInstance {
    @JsonProperty("event_instance")
    private EventInstanceDetails eventInstance;
  }

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class EventInstanceDetails {
    private Long id;
    @JsonProperty("event_id")
    private Long eventId;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private Double ranking;
    @JsonProperty("all_day")
    private boolean allDay;
    @JsonProperty("num_attending")
    private Integer numAttending;
  }

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Department {
    private Long id;
    private String name;
  }
}