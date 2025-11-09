package com.prices.pruebaTecnica.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "PRICES")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private int brandId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private int priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private int productId;

    @Column(name = "PRIORITY", nullable = false)
    private int priority;

    @Column(name = "PRICE", nullable = false)
    private double price;

    @Column(name = "CURR", nullable = false)
    private String curr;
}
