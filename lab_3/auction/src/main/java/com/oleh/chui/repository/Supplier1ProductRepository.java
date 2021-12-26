package com.oleh.chui.repository;

import com.oleh.chui.model.Supplier1Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Supplier1ProductRepository extends JpaRepository<Supplier1Product, Long> {

    Optional<List<Supplier1Product>> findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

    Optional<List<Supplier1Product>> findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
            String category, int minPrice, int maxPrice);

}
