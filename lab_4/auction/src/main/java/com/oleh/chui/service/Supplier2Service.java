package com.oleh.chui.service;

import com.oleh.chui.model.entity.Person;
import com.oleh.chui.model.entity.Product;
import com.oleh.chui.model.entity.ProductForParsing;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Supplier2Service {

    private final RestTemplate restTemplate;
    private final PersonService personService;

    public Supplier2Service(RestTemplate restTemplate, PersonService personService) {
        this.restTemplate = restTemplate;
        this.personService = personService;
    }

    public List<Product> catchDataFromSupplier2() {
        ResponseEntity<ProductForParsing[]> response = restTemplate.getForEntity("http://localhost:8082/price-list", ProductForParsing[].class);

        return buildProductsFromResultOfParsing(Arrays.asList(response.getBody()));
    }

    private List<Product> buildProductsFromResultOfParsing(List<ProductForParsing> productForParsingList) {
        List<Product> products = new ArrayList<>();
        for (ProductForParsing p : productForParsingList) {
            Person seller = personService.getPerson((long) p.getSeller_id()).orElse(null);

            Product product = Product.builder().productId(p.getProductId()).title(p.getTitle()).
                    description(p.getDescription()).category(p.getCategory()).price(p.getPrice()).
                    saleType(p.getSaleType()).startDate(p.getStartDate()).endDate(p.getEndDate()).
                    deliveryType(p.getDeliveryType()).deliveryPrice(p.getDeliveryPrice()).
                    deliveryTimeDays(p.getDeliveryTimeDays()).seller(seller)
                    .build();
            product.setSupplier(2);

            products.add(product);
        }
        return products;
    }

}
