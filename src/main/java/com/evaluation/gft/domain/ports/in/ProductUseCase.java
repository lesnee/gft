package com.evaluation.gft.domain.ports.in;

import com.evaluation.gft.domain.model.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductUseCase {
    Optional<Product> getProductPricesInformation(String productId, String brandId, LocalDateTime requestedDate);
}
