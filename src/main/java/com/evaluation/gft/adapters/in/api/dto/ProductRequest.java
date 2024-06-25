package com.evaluation.gft.adapters.in.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequest {
    private String productId;
    private String brandId;
    private String requestedDate;
}
