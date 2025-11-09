package com.prices.pruebaTecnica.application;

import com.prices.pruebaTecnica.domain.model.Price;
import com.prices.pruebaTecnica.infraestructure.adapter.out.PriceRepository;
import com.prices.pruebaTecnica.domain.dto.PriceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @Author - Sergio Lozano Perez
 * Servicio que se encarga de realizar la llamada al repo
 */
@Service
public class PriceService {

    private final PriceRepository repo;

    public PriceService(PriceRepository repo) {
        this.repo = repo;
    }

    public Optional<PriceResponse> getPrice(LocalDateTime applicationDate, int productId, int brandId) {
        var results = repo.findProduct(applicationDate, productId, brandId);

        if (results.isEmpty()) {
            return Optional.empty();
        }

        Price p = results.get(0);
        var dto = new PriceResponse(p.getProductId(), p.getBrandId(), p.getPriceList(), p.getStartDate(), p.getEndDate(), p.getPrice(), p.getCurr());
        return Optional.of(dto);
    }
}
