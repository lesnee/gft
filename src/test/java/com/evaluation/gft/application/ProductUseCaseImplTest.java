package com.evaluation.gft.application;

import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.domain.ports.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductUseCaseImplTest {

    private ProductRepository repository;
    private ProductUseCaseImpl productUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(ProductRepository.class);
        productUseCase = new ProductUseCaseImpl(repository);
    }

    @Test
    void testGetProductPricesInformation() {
        var productId = "productId";
        var brandId = "brandId";
        var requestedDate = LocalDateTime.of(2000, 5, 1, 0, 0, 0);

        var expectedProduct = createTestProduct(productId, productId);

        when(repository.getProductPricesInformation(productId, brandId, requestedDate))
                .thenReturn(Optional.of(expectedProduct));

        var actualProduct = productUseCase.getProductPricesInformation(productId, brandId, requestedDate);

        assertTrue(actualProduct.isPresent());
        assertEquals(expectedProduct, actualProduct.get());
    }

    @Test
    void testGetProductPricesInformation_ReturnEmpty() {
        var productId = "productId";
        var brandId = "brandId";
        var requestedDate = LocalDateTime.of(2000, 5, 1, 0, 0, 0);

        when(repository.getProductPricesInformation(productId, brandId, requestedDate))
                .thenReturn(Optional.empty());

        var actualProduct = productUseCase.getProductPricesInformation(productId, brandId, requestedDate);

        assertFalse(actualProduct.isPresent());
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
