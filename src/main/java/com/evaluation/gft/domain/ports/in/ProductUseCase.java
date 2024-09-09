package com.evaluation.gft.domain.ports.in;

import com.evaluation.gft.domain.model.Product;

import java.time.LocalDateTime;

public interface ProductUseCase {
    Product getProductPricesInformation(String productId, String brandId, LocalDateTime requestedDate);
}
