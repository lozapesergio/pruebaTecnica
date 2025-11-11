package com.prices.inditex.application;

import com.prices.inditex.domain.exception.NotFoundException;
import com.prices.inditex.domain.model.Price;
import com.prices.inditex.infraestructure.adapter.out.PriceRepository;
import com.prices.inditex.domain.dto.PriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);
    private final PriceRepository repo;

    public PriceServiceImpl(PriceRepository repo) {
        this.repo = repo;
    }

    @Override
    public Price getPrice(LocalDateTime date, int productId, int brandId) {
        log.info("Consultando precio para productId={}, brandId={}, date={}", productId, brandId, date);

        return repo.findProduct(date, productId, brandId)
                .stream()
                .findFirst()
                .map(p -> {
                    log.info("Precio encontrado: priceList={}, price={}", p.getPriceList(), p.getPrice());
                    return new Price(p.getBrandId(), p.getStartDate(), p.getEndDate(), p.getPriceList(),
                            p.getProductId(), p.getPriority(), p.getPrice(), p.getCurr());
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format("No se encontró precio para el producto %d, brand %d en la fecha %s",
                                productId, brandId, date)
                ));
    }
}
