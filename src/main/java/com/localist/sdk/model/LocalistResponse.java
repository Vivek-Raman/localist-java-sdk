package com.localist.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalistResponse<T> {
  private List<T> events;
  private List<T> organizations;
  private List<T> communities;
  private PageInfo page;

  public List<T> getEvents() {
    return events;
  }

  public void setEvents(List<T> events) {
    this.events = events;
  }

  public List<T> getOrganizations() {
    return organizations;
  }

  public void setOrganizations(List<T> organizations) {
    this.organizations = organizations;
  }

  public List<T> getCommunities() {
    return communities;
  }

  public void setCommunities(List<T> communities) {
    this.communities = communities;
  }

  public PageInfo getPage() {
    return page;
  }

  public void setPage(PageInfo page) {
    this.page = page;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class PageInfo {
    private int current;
    private int size;
    private int total;

    public int getCurrent() {
      return current;
    }

    public void setCurrent(int current) {
      this.current = current;
    }

    public int getSize() {
      return size;
    }

    public void setSize(int size) {
      this.size = size;
    }

    public int getTotal() {
      return total;
    }

    public void setTotal(int total) {
      this.total = total;
    }
  }
}