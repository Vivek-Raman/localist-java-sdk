# Localist Java SDK

A Java SDK for interacting with the Localist API. This SDK provides a reactive interface using Spring WebFlux for making API calls to Localist's calendar platform.

## Features

- Full support for Localist API v2
- Reactive programming model using Project Reactor
- Pagination support
- Configurable timeouts and page sizes
- Type-safe response models
- Comprehensive error handling

## Installation

Add this dependency to your Maven project:

```xml
<dependency>
    <groupId>com.localist</groupId>
    <artifactId>localist-java-sdk</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## Configuration

Configure the SDK using Spring Boot properties:

```yaml
localist:
  api:
    base-url: https://your-calendar.localist.com
    access-token: your-access-token # Optional
    default-page-size: 10
    max-page-size: 100
    timeout: 30000 # 30 seconds
```

Or programmatically:

```java
LocalistProperties properties = new LocalistProperties();
properties.setBaseUrl("https://your-calendar.localist.com");
properties.setAccessToken("your-access-token"); // Optional
properties.setDefaultPageSize(10);
properties.setMaxPageSize(100);
properties.setTimeout(30000L);

LocalistAPIClient client = new LocalistAPIClient(properties, WebClient.builder());
```

## Usage

### Getting Events

```java
// Get all events with default pagination
client.getEvents()
    .subscribe(event -> {
        System.out.println(event.getName());
    });

// Get events with custom pagination
client.getEvents(1, 20)
    .subscribe(event -> {
        System.out.println(event.getName());
    });

// Get events by status
client.getEventsByStatus("published", 1, 20)
    .subscribe(event -> {
        System.out.println(event.getName());
    });
```

### Getting Organizations

```java
// Get all organizations
client.getOrganizations()
    .subscribe(org -> {
        System.out.println(org.getName());
    });

// Get a specific organization
client.getOrganization(123L)
    .subscribe(org -> {
        System.out.println(org.getName());
    });
```

### Getting Communities

```java
// Get communities for an organization
client.getCommunities(123L)
    .subscribe(community -> {
        System.out.println(community.getName());
    });

// Get a specific community
client.getCommunity(456L)
    .subscribe(community -> {
        System.out.println(community.getName());
    });
```

## Error Handling

The SDK throws `LocalistAPIException` for any API errors. The exception includes the HTTP status code and error message:

```java
client.getEvent(123L)
    .subscribe(
        event -> System.out.println(event.getName()),
        error -> {
            if (error instanceof LocalistAPIException) {
                LocalistAPIException apiError = (LocalistAPIException) error;
                System.err.println("API Error: " + apiError.getMessage());
                System.err.println("Status: " + apiError.getStatus());
            }
        }
    );
```

## Contributing

Feel free to submit issues and pull requests to improve the SDK.

## License

This project is licensed under the MIT License.
