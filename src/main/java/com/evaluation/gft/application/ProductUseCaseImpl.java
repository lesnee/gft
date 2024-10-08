package com.evaluation.gft.application;

import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import com.evaluation.gft.domain.ports.out.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ProductUseCaseImpl implements ProductUseCase {
    ProductRepository repository;

    @Override
    public Product getProductPricesInformation(String productId, String brandId,
                                                         LocalDateTime requestedDate) {
        return repository.getProductPricesInformation(productId, brandId, requestedDate);
    }
}
