package com.evaluation.gft.domain.ports.out;

import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.exceptions.ProductNotFoundException;

import java.time.LocalDateTime;

public interface ProductRepository {
    Product getProductPricesInformation(String productId, String brandId, LocalDateTime requestedDate) throws ProductNotFoundException;
}
