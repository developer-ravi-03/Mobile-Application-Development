package com.mdt.diagnostic.model;

public class DiagnosticItem {
  private final String title;
  private final String value;
  private final String status;

  public DiagnosticItem(String title, String value, String status) {
    this.title = title;
    this.value = value;
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public String getValue() {
    return value;
  }

  public String getStatus() {
    return status;
  }
}
