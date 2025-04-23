package com.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
  private Long id;
  private String name;
  private String description;
  private String status;
  private ZonedDateTime start;
  private ZonedDateTime end;
  private String url;
  private String localistUrl;
  private GeoInfo location;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_by")
    private String updatedBy;

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("department_name")
    private String departmentName;
}