package com.oleh.chui.repository;

import com.oleh.chui.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

    Optional<List<Product>> findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
            String category, int minPrice, int maxPrice);
}
