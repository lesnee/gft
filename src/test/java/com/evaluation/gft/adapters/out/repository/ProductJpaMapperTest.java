package com.evaluation.gft.adapters.out.repository;

import com.evaluation.gft.adapters.out.repository.mapper.ProductJpaMapper;
import com.evaluation.gft.domain.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ProductJpaMapperTest {
    private ProductJpaMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ProductJpaMapper();
    }

    @Test
    void testMapToResponse() {
        var productJpa = createTestJpaProduct();

        var actual = mapper.mapToProduct(productJpa);

        var expected = createTestProduct();

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

    private Prices createTestJpaProduct() {
        return new Prices(
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
