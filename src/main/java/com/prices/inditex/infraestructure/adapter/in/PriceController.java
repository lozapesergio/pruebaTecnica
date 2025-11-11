package com.prices.inditex.infraestructure.adapter.in;

import com.prices.inditex.application.PriceServiceImpl;
import com.prices.inditex.domain.dto.PriceResponse;
import com.prices.inditex.domain.model.Price;
import com.prices.inditex.infraestructure.adapter.in.mappers.PriceResponseMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceServiceImpl service;

    public PriceController(PriceServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getPrice(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") int productId,
            @RequestParam("brandId") int brandId
    ) {

        Price price = service.getPrice(date, productId, brandId);
        PriceResponse response = PriceResponseMapper.toPriceResponse(price);

        return ResponseEntity.ok(response);
    }
}
