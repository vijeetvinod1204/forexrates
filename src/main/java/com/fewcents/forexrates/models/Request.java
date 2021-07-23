package com.fewcents.forexrates.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Request {

  private String from;
  private String to;
  private LocalDate date;
  private BigDecimal rate;

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }


  public Request(String from, String to, LocalDate date, BigDecimal rate) {
    this.from = from;
    this.to = to;
    this.date = date;
    this.rate = rate;
  }
}
