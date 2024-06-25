package com.evaluation.gft.adapters.in.api;

import com.evaluation.gft.adapters.in.api.dto.ProductRequest;
import com.evaluation.gft.adapters.in.api.dto.ProductResponse;
import com.evaluation.gft.adapters.in.api.mapper.ProductResponseMapper;
import com.evaluation.gft.domain.ports.in.ProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    ProductUseCase useCase;
    ProductResponseMapper mapper;

    @PostMapping("/price_info")
    public ResponseEntity<ProductResponse> getProductPricesInformation(@RequestBody ProductRequest productRequest) {

        if (productRequest == null ||
                productRequest.getProductId() == null ||
                productRequest.getBrandId() == null ||
                productRequest.getRequestedDate() == null) {
            return ResponseEntity.badRequest().build();
        }

        LocalDateTime date;
        try {
            date = parseInputDate(productRequest.getRequestedDate());
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }

        var product = useCase.getProductPricesInformation(
                    productRequest.getProductId(), productRequest.getBrandId(), date);

        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(mapper.mapToResponse(product.get()), HttpStatus.OK);
    }

    private LocalDateTime parseInputDate(String date) {
        String pattern = "yyyy-MM-dd-HH.mm.ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return LocalDateTime.parse(date, formatter);
    }
}
