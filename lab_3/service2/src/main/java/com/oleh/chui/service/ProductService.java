package com.oleh.chui.service;

import com.oleh.chui.model.Product;
import com.oleh.chui.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    final private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product readDetails(Long id) {
        Optional<Product> row = productRepository.findProductByProductId(id);

        return row.orElse(new Product());
    }

    public List<Product> findProductsWithPagination(int offset) {
        List<Product> productList = productRepository.findAll(PageRequest.of(offset, 5000)).toList();

        for (Product product : productList) {
            product.setSeller(null);
            product.setDeliveryPrice(0);
            product.setDeliveryType(null);
            product.setDeliveryTimeDays(0);
            product.setDescription(null);
            product.setStartDate(null);
            product.setEndDate(null);
            product.setSaleType(null);
        }

        return productList;
    }

//    public void generateRandomProducts(int count) {
//        for (int i = 0; i < count; i++) {
//
//            Product product = generateRandomProduct();
//            productRepository.save(product);
//
//        }
//    }
//
//    private Product generateRandomProduct() {
//        String[] descriptions = {"Low quality", "Medium quality", "High quality"};
//        String[] saleTypes = {"selling", "auction"};
//        String[] sellers = {"Nika", "Jakob", "Herta", "George", "Kan", "Tom", "Bin", "Will", "Karl", "Peter"};
//
//        Product product = new Product();
//
//        product.setTitle(String.format("Title %d", getRandomNumber(1, 50000)));
//        product.setDescription(descriptions[getRandomNumber(0, descriptions.length)]);
//        product.setCategory(String.format("Category %d", getRandomNumber(1, 200)));
//        product.setPrice(getRandomNumber(1, 100000));
//        product.setSaleType(saleTypes[getRandomNumber(0, saleTypes.length)]);
//        if (product.getSaleType().equals("auction")) {
//            product.setStartDate(LocalDate.of(2021, getRandomNumber(1, 12), getRandomNumber(1, 25)));
//            product.setEndDate(product.getStartDate().plusMonths(2));
//        }
//        product.setDeliveryType("mail");
//        product.setDeliveryPrice(getRandomNumber(1, 20));
//        product.setSeller(sellers[getRandomNumber(0, sellers.length)]);
//        product.setDeliveryTimeDays(getRandomNumber(1, 5));
//
//        return product;
//    }
//
//    private int getRandomNumber(int min, int max) {
//        return (int) ((Math.random() * (max - min)) + min);
//    }
}
