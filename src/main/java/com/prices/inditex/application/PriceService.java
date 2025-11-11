package com.prices.inditex.application;

import com.prices.inditex.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceService {
    Price getPrice(LocalDateTime date, int productId, int brandId);
}
