package dev.vivekraman.localist.sdk.internal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "localist.api")
public class LocalistProperties {
  private String baseUrl;
  private String accessToken;
  private int defaultPageSize = 10;
  private int maxPageSize = 100;
  private long timeout = 30000;
}
