package com.evaluation.gft.domain.ports.out;

import com.evaluation.gft.domain.model.Product;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductPricesInformation(String productId, String brandId, LocalDateTime requestedDate);
}
