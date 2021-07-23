package com.fewcents.forexrates.controllers;

import com.fewcents.forexrates.models.ForexRates;
import com.fewcents.forexrates.models.Key;
import com.fewcents.forexrates.models.Request;
import com.fewcents.forexrates.repository.ForexRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {

  @Autowired
  private ForexRepository forexRepository;

  @GetMapping("/getRateForAPair")
  public ResponseEntity<BigDecimal> getRate(@RequestParam String from,
      @RequestParam String to,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    try {
      ForexRates forexRates = forexRepository.findByKey(new Key(from, to, date));
      if (forexRates == null) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(forexRates.getRate(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/addRateForAPair")
  public ResponseEntity<Request> addRate(@RequestBody Request request) {
    try {
      ForexRates forexRates = forexRepository.save(new ForexRates(new Key(request.getFrom(),
          request.getTo(), request.getDate()), request.getRate()));
      return new ResponseEntity<>(request, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/updateRateForAPair")
  public ResponseEntity<Request> updateRate(@RequestBody Request request) {
    try {
      ForexRates forexRates = forexRepository.findByKey(new Key(request.getFrom(),
              request.getTo(), request.getDate()));
      if (forexRates == null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      else {
        forexRates.setRate(request.getRate());
        forexRepository.save(forexRates);
        return new ResponseEntity<>(request, HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
