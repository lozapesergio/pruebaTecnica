package com.prices.pruebaTecnica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PriceResponse {

    private final int productId;
    private final int brandId;
    private final int priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final double price;
    private final String curr;


}
