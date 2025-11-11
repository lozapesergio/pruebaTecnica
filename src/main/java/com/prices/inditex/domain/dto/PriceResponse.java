package com.prices.inditex.domain.dto;

import java.time.LocalDateTime;

public class PriceResponse {

    private final int productId;
    private final int brandId;
    private final int priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final double price;
    private final String curr;

    public PriceResponse(int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, double price, String curr) {
        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.curr = curr;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
