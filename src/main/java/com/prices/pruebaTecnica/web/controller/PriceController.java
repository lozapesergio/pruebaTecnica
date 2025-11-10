package com.prices.pruebaTecnica.web.controller;

import com.prices.pruebaTecnica.application.PriceService;
import com.prices.pruebaTecnica.domain.dto.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceService service;

    public PriceController(PriceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId
    ) {

        PriceResponse resp = service.getPrice(date, productId, brandId);

        return ResponseEntity.ok(resp);
    }
}
