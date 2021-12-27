package com.oleh.chui.service;

import com.oleh.chui.model.entity.Person;
import com.oleh.chui.model.entity.Product;
import com.oleh.chui.model.util.SaleType;
import com.oleh.chui.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Supplier1Service supplier1Service;
    private final Supplier2Service supplier2Service;

    public ProductService(ProductRepository productRepository, Supplier1Service supplier1Service, Supplier2Service supplier2Service) {
        this.productRepository = productRepository;
        this.supplier1Service = supplier1Service;
        this.supplier2Service = supplier2Service;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product createProduct(String title, String description, String category,
                                 int price, String saleType, String startDateString,
                                 String endDateString, String deliveryType, int deliveryPrice,
                                 int deliveryTimeDays, Person seller) {


        Product product = buildProduct(title, description, category, price, saleType, startDateString,
                endDateString, deliveryType, deliveryPrice, deliveryTimeDays, seller);

        return productRepository.save(product);

    }

    public Optional<Product> updateProduct(Long productId, String title, String description, String category,
                                           int price, String saleType, String startDateString,
                                           String endDateString, String deliveryType, int deliveryPrice,
                                           int deliveryTimeDays, Person seller) {

        Product product = buildProduct(title, description, category, price, saleType, startDateString,
                endDateString, deliveryType, deliveryPrice, deliveryTimeDays, seller);

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
            return Optional.of(item);
        } else {
            return Optional.empty();
        }
    }

    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Product> getProduct(final Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll().stream().filter(p -> p.getSupplier() == 0).collect(Collectors.toList());
        List<Product> supplier1ProductList = supplier1Service.catchDataFromSupplier1();
        List<Product> supplier2ProductList = supplier2Service.catchDataFromSupplier2();

        productList.addAll(supplier1ProductList);
        productList.addAll(supplier2ProductList);

        return productList;
    }

    private Product buildProduct(String title, String description, String category,
                                 int price, String saleTypeString, String startDateString,
                                 String endDateString, String deliveryType, int deliveryPrice,
                                 int deliveryTimeDays, Person seller) {

        LocalDate startDate = startDateString == null ? null : LocalDate.parse(startDateString);
        LocalDate endDate = endDateString == null ? null : LocalDate.parse(endDateString);
        SaleType saleType = saleTypeString == null ? null : SaleType.valueOf(saleTypeString);

        final Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setSaleType(saleType);
        product.setStartDate(startDate);
        product.setEndDate(endDate);
        product.setDeliveryType(deliveryType);
        product.setDeliveryPrice(deliveryPrice);
        product.setDeliveryTimeDays(deliveryTimeDays);
        product.setSeller(seller);
        product.setSupplier(0);

        return product;
    }
}
