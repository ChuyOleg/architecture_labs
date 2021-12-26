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

    public void create(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void update(Product product, Long productId) {
        Optional<Product> row = productRepository.findById(productId);
        if (row.isPresent()) {
            Product item = row.get();
            if (product.getTitle() != null) {
                item.setTitle(product.getTitle());
            }
            if (product.getCategory() != null) {
                item.setCategory(product.getCategory());
            }
            if (product.getPrice() != 0) {
                item.setPrice(product.getPrice());
            }
            if (product.getDeliveryPrice() != 0) {
                item.setDeliveryPrice(product.getPrice());
            }
            if (product.getDeliveryTimeDays() != 0) {
                item.setDeliveryTimeDays(product.getDeliveryTimeDays());
            }
            if (product.getDeliveryType() != null) {
                item.setDeliveryType(product.getDeliveryType());
            }
            if (product.getDescription() != null) {
                item.setDescription(product.getDescription());
            }
            if (product.getStartDate() != null) {
                item.setStartDate(product.getStartDate());
            }
            if (product.getEndDate() != null) {
                item.setEndDate(product.getEndDate());
            }
            if (product.getSeller() != null) {
                item.setSeller(product.getSeller());
            }
            if (product.getSaleType() != null) {
                item.setSaleType(product.getSaleType());
            }
            productRepository.save(item);
        }
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

    public void delete(long productId) {
        productRepository.deleteById(productId);
    }

}
