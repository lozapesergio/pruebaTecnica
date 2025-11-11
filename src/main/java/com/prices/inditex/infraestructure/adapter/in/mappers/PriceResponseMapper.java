package com.prices.inditex.infraestructure.adapter.in.mappers;

import com.prices.inditex.domain.dto.PriceResponse;
import com.prices.inditex.domain.model.Price;

public class PriceResponseMapper {

    private PriceResponseMapper() {
    }

    public static PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice(),
                price.getCurr()
        );
    }
}
