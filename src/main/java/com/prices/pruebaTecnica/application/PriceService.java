package com.prices.pruebaTecnica.application;

import com.prices.pruebaTecnica.domain.exception.NotFoundException;
import com.prices.pruebaTecnica.domain.model.Price;
import com.prices.pruebaTecnica.infraestructure.adapter.out.PriceRepository;
import com.prices.pruebaTecnica.domain.dto.PriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @Author - Sergio Lozano Perez
 * Servicio que se encarga de realizar la llamada al repo
 */
@Slf4j
@Service
public class PriceService {

    private final PriceRepository repo;

    public PriceService(PriceRepository repo) {
        this.repo = repo;
    }

    public PriceResponse getPrice(LocalDateTime date, int productId, int brandId) {
        log.info("Consultando precio para productId={}, brandId={}, date={}", productId, brandId, date);

        return repo.findProduct(date, productId, brandId)
                .stream()
                .findFirst()
                .map(p -> {
                    log.info("Precio encontrado: priceList={}, price={}", p.getPriceList(), p.getPrice());
                    return new PriceResponse(p.getProductId(), p.getBrandId(), p.getPriceList(),
                            p.getStartDate(), p.getEndDate(), p.getPrice(), p.getCurr());
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format("No se encontró precio para el producto %d, cadena %d en la fecha %s",
                                productId, brandId, date)
                ));
    }
}
