package com.oleh.chui.service;

import com.oleh.chui.model.Product;
import com.oleh.chui.model.Supplier2Product;
import com.oleh.chui.repository.Supplier2ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class Supplier2Service {

    private final RestTemplate restTemplate;
    private final Supplier2ProductRepository repository;
    private final String PRICE_LIST_URL = "http://localhost:8082/price-list/page/";
    private final String PART_OF_DETAILS_URL = "http://localhost:8082/details/";

    public Supplier2Service(RestTemplate restTemplate, Supplier2ProductRepository repository) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public List<Supplier2Product> catchDataFromSupplier2() {
        List<Supplier2Product> fullList = new ArrayList<>();
        int page = 0;

        while (true) {
            ResponseEntity<Supplier2Product[]> response = restTemplate.getForEntity(PRICE_LIST_URL + page, Supplier2Product[].class);
            Supplier2Product[] priceList = response.getBody();
            if (priceList == null) return fullList;
            if (priceList.length == 0) break;

            for (Supplier2Product product : priceList) {
                final String FULL_DETAILS_URL = PART_OF_DETAILS_URL + product.getProductId();
                fullList.add(restTemplate.getForObject(FULL_DETAILS_URL, Supplier2Product.class));
            }
            page++;
        }

        return fullList;
    }

    public void updateOrCreate(Supplier2Product product, Long productId) {
        Optional<Supplier2Product> row = repository.findById(productId);
        if (row.isPresent()) {
            Supplier2Product item = row.get();
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

    public List<Supplier2Product> readByCategoryAndPrice(String category, int minPrice, int maxPrice) {
        Optional<List<Supplier2Product>> rows = repository.findProductsByCategoryAndPriceGreaterThanEqualAndPriceLessThanEqual(
                category, minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

    public List<Supplier2Product> readByPrice(int minPrice, int maxPrice) {
        Optional<List<Supplier2Product>> rows = repository.findProductsByPriceGreaterThanEqualAndPriceLessThanEqual(
                minPrice, maxPrice
        );

        return rows.orElse(Collections.emptyList());
    }

}
