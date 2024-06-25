package com.evaluation.gft.adapters.out.repository;

import com.evaluation.gft.adapters.out.repository.mapper.ProductJpaMapper;
import com.evaluation.gft.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductRepositoryImplementationTest {
    private ProductRepositoryJpa repositoryJpa;
    private ProductJpaMapper mapper;
    private ProductRepositoryImplementation repository;

    @BeforeEach
    void setUp() {
        repositoryJpa = mock(ProductRepositoryJpa.class);
        mapper = mock(ProductJpaMapper.class);
        repository = new ProductRepositoryImplementation(repositoryJpa, mapper);
    }

    @Test
    void testGetProductPricesInformation() {
        var productId = "productId";
        var brandId = "brandId";
        var requestedDate = LocalDateTime.of(2000, 5, 1, 0, 0, 0);

        var productJpa = createTestProductJpa(productId, productId);
        var expectedProduct = createTestProduct(productId, brandId);

        when(repositoryJpa.getSortedProductPricesInformation(productId, brandId, requestedDate))
                .thenReturn(Optional.of(productJpa));
        when(mapper.mapToProduct(productJpa)).thenReturn(expectedProduct);

        var actualProduct = repository.getProductPricesInformation(productId, brandId, requestedDate);

        assertTrue(actualProduct.isPresent());
        assertEquals(expectedProduct, actualProduct.get());
    }

    @Test
    void testGetProductPricesInformation_ReturnEmpty() {
        var productId = "productId";
        var brandId = "brandId";
        var requestedDate = LocalDateTime.of(2000, 5, 1, 0, 0, 0);

        when(repositoryJpa.getSortedProductPricesInformation(productId, brandId, requestedDate))
                .thenReturn(Optional.empty());

        var actualProduct = repository.getProductPricesInformation(productId, brandId, requestedDate);

        assertFalse(actualProduct.isPresent());
    }

    private Prices createTestProductJpa(String productId, String brandId) {
        return new Prices(
                productId,
                brandId,
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                LocalDateTime.of(3000, 1, 1, 0, 0, 0),
                1,
                0,
                2.0F,
                "EUR");
    }

    private Product createTestProduct(String productId, String brandId) {
        return new Product(
                productId,
                brandId,
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                LocalDateTime.of(3000, 1, 1, 0, 0, 0),
                1,
                0,
                2.0F,
                "EUR");
    }
}
