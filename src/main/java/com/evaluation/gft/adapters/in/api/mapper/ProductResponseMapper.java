package com.evaluation.gft.adapters.in.api.mapper;

import com.evaluation.gft.adapters.in.api.ProductResponse;
import com.evaluation.gft.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductResponseMapper {
    public ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
                product.getProductId(),
                product.getBrandId(),
                product.getPriceList(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice());
    }
}
