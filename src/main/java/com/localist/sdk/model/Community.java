package com.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Community {
  private Long id;
  private String name;
  private String description;
  private String url;
  private String status;
}