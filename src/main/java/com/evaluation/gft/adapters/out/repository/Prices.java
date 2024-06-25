package com.evaluation.gft.adapters.out.repository;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Prices {
    @Nonnull private String productId;
    @Nonnull private String brandId;
    @Nonnull private LocalDateTime startDate;
    @Nonnull private LocalDateTime endDate;
    @Id private Integer priceList;
    @Nonnull private Integer priority;
    @Nonnull private Float price;
    @Nonnull private String currency;
}
