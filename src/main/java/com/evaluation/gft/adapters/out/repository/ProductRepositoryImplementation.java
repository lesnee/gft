package com.evaluation.gft.adapters.out.repository;

import com.evaluation.gft.adapters.out.repository.mapper.ProductJpaMapper;
import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.domain.ports.out.ProductRepository;
import com.evaluation.gft.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Repository
public class ProductRepositoryImplementation implements ProductRepository {
    ProductRepositoryJpa repositoryJpa;
    ProductJpaMapper mapper;

    @Override
    public Product getProductPricesInformation(String productId, String brandId,
                                                         LocalDateTime requestedDate) {
        var productJpa = repositoryJpa.getSortedProductPricesInformation(productId, brandId, requestedDate);
        if (productJpa.isEmpty()) {
            throw new ProductNotFoundException();
        }
        return mapper.mapToProduct(productJpa.get());
    }
}
