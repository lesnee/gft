package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.adapters.in.api.mapper.ProductResponseMapper;
import com.evaluation.gft.adapters.in.api.model.ProductResponse;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import com.evaluation.gft.domain.model.Product;
import com.evaluation.gft.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    private ProductController productController;
    private ProductUseCase useCase;
    private ProductResponseMapper mapper;

    @BeforeEach
    void setup() {
        useCase = mock(ProductUseCase.class);
        mapper = mock(ProductResponseMapper.class);
        productController = new ProductController(useCase, mapper);
    }

    @Test
    void testGetProductPricesInformation_Success() {
        var productId = "34554";
        var brandId = "1";
        var date = LocalDateTime.of(2000, 1, 1, 12, 0, 0);

        var product = createTestProduct(productId, brandId);
        var response = createTestProductResponse(productId, brandId);

        when(useCase.getProductPricesInformation(productId, brandId, date))
                .thenReturn(product);
        when(mapper.mapToResponse(product)).thenReturn(response);

        var result = productController.getProductPricesInformation(productId, brandId, date);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetProductPricesInformation_NotFound() {
        var productId = "34554";
        var brandId = "1";
        var date = LocalDateTime.of(2000, 1, 1, 12, 0, 0);

        when(useCase.getProductPricesInformation(any(), any(), any())).thenThrow(ProductNotFoundException.class);

        assertThrows(ProductNotFoundException.class,
                () -> productController.getProductPricesInformation(productId, brandId, date));
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

    private ProductResponse createTestProductResponse(String productId, String brandId) {
        return new ProductResponse(
                productId,
                brandId,
                1,
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                LocalDateTime.of(3000, 1, 1, 0, 0, 0),
                2.0F);
    }
}