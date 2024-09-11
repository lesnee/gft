package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.adapters.in.api.mapper.ProductResponseMapper;
import com.evaluation.gft.adapters.in.api.model.ProductResponse;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController implements ProductsApi {
    ProductUseCase useCase;
    ProductResponseMapper mapper;

    @Override
    public ResponseEntity<ProductResponse> getProductPricesInformation(
            String productId,
            String brandId,
            LocalDateTime requestedDate) {

        var product = useCase.getProductPricesInformation(productId, brandId, requestedDate);

        return new ResponseEntity<>(mapper.mapToResponse(product), HttpStatus.OK);
    }
}
