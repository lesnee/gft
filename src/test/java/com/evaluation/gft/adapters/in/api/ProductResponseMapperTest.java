package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.adapters.in.api.dto.ProductResponse;
import com.evaluation.gft.adapters.in.api.mapper.ProductResponseMapper;
import com.evaluation.gft.domain.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ProductResponseMapperTest {

    private ProductResponseMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ProductResponseMapper();
    }

    @Test
    void testMapToResponse() {
        var product = createTestProduct();

        var actual = mapper.mapToResponse(product);

        var expected = new ProductResponse(
                product.getProductId(),
                product.getBrandId(),
                product.getPriceList(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice());

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
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
}