package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

  @LocalServerPort
  protected int port;

  @Autowired
  private WebTestClient webClient;

  @Test
  public void requestExistingResourceAtValidPathWithCsvAcceptHeaderShouldReturn200() {
    this.webClient.get()
        .uri("/clients/12345").accept(MediaType.valueOf(DemoApplication.MEDIA_TYPE_CSV))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.OK)
        .expectBody(String.class).isEqualTo("a,dummy,csv,string");
  }

  @Test
  public void requestNonExistingResourceAtValidPathWithCsvAcceptHeaderShouldReturn406() {
    this.webClient.get()
        .uri("/clients/666").accept(MediaType.valueOf(DemoApplication.MEDIA_TYPE_CSV))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE) // .isEqualTo(HttpStatus.NOT_FOUND)
        .expectBody().isEmpty();
  }

  @Test
  public void requestInvalidPathWithCsvAcceptHeaderShouldReturn404() {
    this.webClient.get()
        .uri("/nonexistingPath").accept(MediaType.valueOf(DemoApplication.MEDIA_TYPE_CSV))
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.NOT_FOUND)
        .expectBody().isEmpty();
  }

}
