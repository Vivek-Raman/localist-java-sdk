package dev.vivekraman.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalistEventsResponse {
  private List<EventHolder> events;
  private PageInfo page;

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class EventHolder {
    private Event event;
  }

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PageInfo {
    private int current;
    private int size;
    private int total;
  }
}