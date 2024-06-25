package com.evaluation.gft.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ProductRepositoryJpa extends JpaRepository<Prices, Integer> {

    @Query(value =
            "SELECT * " +
            "FROM prices p " +
            "WHERE p.product_id = :productId " +
                    "AND p.brand_id = :brandId " +
                    "AND :requestedDate BETWEEN p.start_date " +
                    "AND p.end_date " +
            "ORDER BY p.priority DESC LIMIT 1",
            nativeQuery = true)
    Optional<Prices> getSortedProductPricesInformation(String productId, String brandId,
                                                           LocalDateTime requestedDate);
}
