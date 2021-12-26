package com.oleh.chui.service;

import com.oleh.chui.model.Product;
import com.oleh.chui.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(Product product) {
        productRepository.save(product);
    }

    public List<Product> readByCategoryAndPrice(String category, int minPrice, int maxPrice) {
        Optional<List<Product>> rows = productRepository.findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
                category, minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

    public List<Product> readByPrice(int minPrice, int maxPrice) {
        Optional<List<Product>> rows = productRepository.findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(
                minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

}
