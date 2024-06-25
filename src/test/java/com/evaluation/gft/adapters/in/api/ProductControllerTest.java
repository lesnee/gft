package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.adapters.in.api.dto.ProductRequest;
import com.evaluation.gft.adapters.in.api.dto.ProductResponse;
import com.evaluation.gft.adapters.in.api.mapper.ProductResponseMapper;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import com.evaluation.gft.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var date = "2000-01-01-12.00.00";
        var request = new ProductRequest("productId", "brandId", date);
        var product = createTestProduct();
        var response = createTestProductResponse();

        when(useCase.getProductPricesInformation(
                request.getProductId(),
                request.getBrandId(),
                LocalDateTime.of(2000, 1, 1, 12, 0, 0))
        ).thenReturn(Optional.of(product));
        when(mapper.mapToResponse(product)).thenReturn(response);

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testGetProductPricesInformation_NotFound() {
        var date = "2000-01-01-12.00.00";
        var request = new ProductRequest("productId", "brandId", date);

        when(useCase.getProductPricesInformation(any(), any(), any())).thenReturn(Optional.empty());

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }


    @Test
    void testGetProductPricesInformation_InvalidDate() {
        var invalidDate = "invalidDate";
        var request = new ProductRequest("productId", "brandId", invalidDate);

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testGetProductPricesInformation_withNullDate() {
        var request = new ProductRequest("productId", "brandId", null);

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testGetProductPricesInformation_withNullProductId() {
        var date = "2000-01-01-12.00.00";
        var request = new ProductRequest(null, "brandId", date);

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testGetProductPricesInformation_withNullBrandId() {
        var date = "2000-01-01-12.00.00";
        var request = new ProductRequest("productId", null, date);

        var result = productController.getProductPricesInformation(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void testGetProductPricesInformation_withNullRequest() {

        ResponseEntity<ProductResponse> result = productController.getProductPricesInformation(null);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    private Product createTestProduct() {
        return new Product(
                "productId",
                "brandId",
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                LocalDateTime.of(3000, 1, 1, 0, 0, 0),
                1,
                0,
                2.0F,
                "EUR");
    }

    private ProductResponse createTestProductResponse() {
        return new ProductResponse(
                "productId",
                "brandId",
                1,
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                LocalDateTime.of(3000, 1, 1, 0, 0, 0),
                2.0F);
    }
}