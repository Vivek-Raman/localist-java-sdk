package dev.vivekraman.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoInfo {
  private String city;
  private String country;
  private Double latitude;
  private Double longitude;
  private String state;
  private String street;
  private String zip;
}