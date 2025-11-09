package com.prices.pruebaTecnica;

import com.prices.pruebaTecnica.domain.dto.PriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    private String baseUrl() { return "http://localhost:" + port + "/api/prices"; }

    private PriceResponse call(String dateIso, int productId, int brandId) {
        String url = String.format("%s?applicationDate=%s&productId=%d&brandId=%d", baseUrl(), dateIso, productId, brandId);
        ResponseEntity<PriceResponse> res = rest.getForEntity(url, PriceResponse.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        return res.getBody();
    }

    @Test
    public void test1_2020_06_14_10_00() {
        var r = call("2020-06-14T10:00:00", 35455, 1);
        assertThat(r.getPriceList()).isEqualTo(1);
        assertThat(r.getPrice()).isEqualTo(35.50);
    }

    @Test
    public void test2_2020_06_14_16_00() {
        var r = call("2020-06-14T16:00:00", 35455, 1);
        assertThat(r.getPriceList()).isEqualTo(2);
        assertThat(r.getPrice()).isEqualTo(25.45);
    }

    @Test
    public void test3_2020_06_14_21_00() {
        var r = call("2020-06-14T21:00:00", 35455, 1);
        assertThat(r.getPriceList()).isEqualTo(1);
        assertThat(r.getPrice()).isEqualTo(35.50);
    }

    @Test
    public void test4_2020_06_15_10_00() {
        var r = call("2020-06-15T10:00:00", 35455, 1);
        assertThat(r.getPriceList()).isEqualTo(3);
        assertThat(r.getPrice()).isEqualTo(30.50);
    }

    @Test
    public void test5_2020_06_16_21_00() {
        var r = call("2020-06-16T21:00:00", 35455, 1);
        assertThat(r.getPriceList()).isEqualTo(4);
        assertThat(r.getPrice()).isEqualTo(38.95);
    }
}