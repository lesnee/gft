package com.evaluation.gft.adapters.out.repository.mapper;

import com.evaluation.gft.adapters.out.repository.Prices;
import com.evaluation.gft.domain.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductJpaMapper {

    public Product mapToProduct(Prices productJpa) {
        return new Product(
                productJpa.getProductId(),
                productJpa.getBrandId(),
                productJpa.getStartDate(),
                productJpa.getEndDate(),
                productJpa.getPriceList(),
                productJpa.getPriority(),
                productJpa.getPrice(),
                productJpa.getCurrency());
    }
}
