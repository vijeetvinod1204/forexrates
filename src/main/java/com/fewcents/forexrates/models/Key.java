package com.fewcents.forexrates.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Key implements Serializable {
  @Column(name = "from_currency")
  private String from;

  @Column(name = "to_currency")
  private String to;

  @Column(name = "date")
  private LocalDate date;

  public Key(String from, String to, LocalDate date) {
    this.from = from;
    this.to = to;
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Key key = (Key) o;
    return from.equals(key.from) && to.equals(key.to) && date.equals(key.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(from, to, date);
  }

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

  public Key(){

  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Key.class.getSimpleName() + "[", "]")
        .add("from='" + from + "'")
        .add("to='" + to + "'")
        .add("date=" + date)
        .toString();
  }
}
