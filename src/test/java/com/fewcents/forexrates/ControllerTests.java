package com.fewcents.forexrates;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fewcents.forexrates.controllers.ApplicationController;
import com.fewcents.forexrates.models.ForexRates;
import com.fewcents.forexrates.models.Key;
import com.fewcents.forexrates.models.Request;
import com.fewcents.forexrates.repository.ForexRepository;
import com.fewcents.forexrates.util.JsonUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
public class ControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ForexRepository forexRepository;

  @Test
  public void getRateForAPairTest() throws Exception {
    Key key = new Key("USD", "INR", LocalDate.now());
    ForexRates forexRates = new ForexRates(key,
        new BigDecimal("74.44"));
    given(forexRepository.findByKey(key)).willReturn(forexRates);
    mockMvc.perform(
        get("/api/getRateForAPair").param("from", "USD").param("to", "INR")
            .param("date", "2021-07-23")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("74.44"));
  }

  @Test
  public void addRateForAPairTest() throws Exception {
    Key key = new Key("USD", "INR", LocalDate.now());
    ForexRates forexRates = new ForexRates(key,
        new BigDecimal("74.44"));
    Request request = new Request("USD", "INR", LocalDate.now(), new BigDecimal("74.44"));
    given(forexRepository.save(Mockito.any())).willReturn(forexRates);
        mockMvc.perform(
          post("/api/addRateForAPair")
              .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(request)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.rate", is(74.44)));
    verify(forexRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    reset(forexRepository);
  }

  @Test
  public void updateRateForAPairTest() throws Exception {
    Key key = new Key("USD", "INR", LocalDate.now());
    ForexRates forexRates = new ForexRates(key,
        new BigDecimal("74.44"));
    Request request = new Request("USD", "INR", LocalDate.now(), new BigDecimal("74.44"));
    given(forexRepository.findByKey(key)).willReturn(forexRates);
    given(forexRepository.save(Mockito.any())).willReturn(forexRates);
      mockMvc.perform(
        put("/api/updateRateForAPair")
            .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.rate", is(74.44)));
    verify(forexRepository, VerificationModeFactory.times(1)).findByKey(Mockito.any());
    verify(forexRepository, VerificationModeFactory.times(1)).save(Mockito.any());
    reset(forexRepository);
  }
}
