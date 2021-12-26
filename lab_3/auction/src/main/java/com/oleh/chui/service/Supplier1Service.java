package com.oleh.chui.service;

import com.oleh.chui.model.Supplier1Product;
import com.oleh.chui.repository.Supplier1ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Supplier1Service {

    private final RestTemplate restTemplate;
    private final Supplier1ProductRepository repository;
    private final String URL = "http://localhost:8081/search";

    public Supplier1Service(RestTemplate restTemplate, Supplier1ProductRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    public List<Supplier1Product> catchDataFromSupplier1() {
        ResponseEntity<Supplier1Product[]> response = restTemplate.getForEntity(URL, Supplier1Product[].class);

        return Arrays.asList(response.getBody());
    }

    public void updateOrCreate(Supplier1Product product, Long productId) {
        Optional<Supplier1Product> row = repository.findById(productId);
        if (row.isPresent()) {
            Supplier1Product item = row.get();
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
            repository.save(item);
        } else {
            repository.save(product);
        }
    }

    public List<Supplier1Product> readByCategoryAndPrice(String category, int minPrice, int maxPrice) {
        Optional<List<Supplier1Product>> rows = repository.findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
                category, minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

    public List<Supplier1Product> readByPrice(int minPrice, int maxPrice) {
        Optional<List<Supplier1Product>> rows = repository.findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(
                minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

}
