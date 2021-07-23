package com.fewcents.forexrates.models;

import java.math.BigDecimal;
import java.util.StringJoiner;
import javax.persistence.*;

@Entity
@Table(name = "forex")
public class ForexRates {

  @EmbeddedId
  private Key key;

  @Column(name = "rate")
  private BigDecimal rate;

  public BigDecimal getRate() {
    return rate;
  }

  public void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  public Key getKey() {
    return key;
  }

  public void setKey(Key key) {
    this.key = key;
  }

  public ForexRates() {
  }
  public ForexRates(Key key, BigDecimal rate) {
    this.key = key;
    this.rate = rate;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ForexRates.class.getSimpleName() + "[", "]")
        .add("key=" + key)
        .add("rate=" + rate)
        .toString();
  }
}
