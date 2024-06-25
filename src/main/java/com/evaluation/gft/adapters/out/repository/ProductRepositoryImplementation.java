package com.evaluation.gft.adapters.out.repository;

import com.evaluation.gft.adapters.out.repository.mapper.ProductJpaMapper;
import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.domain.ports.out.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProductRepositoryImplementation implements ProductRepository {
    ProductRepositoryJpa repositoryJpa;
    ProductJpaMapper mapper;

    @Override
    public Optional<Product> getProductPricesInformation(String productId, String brandId,
                                                         LocalDateTime requestedDate) {
        var productJpa = repositoryJpa.getSortedProductPricesInformation(productId, brandId, requestedDate);
        if (productJpa.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.mapToProduct(productJpa.get()));
    }
}
