package com.evaluation.gft.application;

import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import com.evaluation.gft.domain.ports.out.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductService implements ProductUseCase {
    ProductRepository repository;

    @Override
    public Optional<Product> getProductPricesInformation(String productId, String brandId,
                                                         LocalDateTime requestedDate) {
        return repository.getProductPricesInformation(productId, brandId, requestedDate);
    }
}
