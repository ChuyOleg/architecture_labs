package com.oleh.chui.repository;

import com.oleh.chui.model.Supplier2Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface Supplier2ProductRepository extends JpaRepository<Supplier2Product, Long> {

    Optional<List<Supplier2Product>> findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(int minPrice, int maxPrice);

    Optional<List<Supplier2Product>> findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
            String category, int minPrice, int maxPrice);

}
