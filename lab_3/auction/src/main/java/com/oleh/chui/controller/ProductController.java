package com.oleh.chui.controller;

import com.oleh.chui.model.Product;
import com.oleh.chui.model.Supplier1Product;
import com.oleh.chui.model.Supplier2Product;
import com.oleh.chui.model.UniversalProduct;
import com.oleh.chui.service.ProductService;
import com.oleh.chui.service.Supplier1Service;
import com.oleh.chui.service.Supplier2Service;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProductController implements Runnable {

    private final ProductService productService;
    private final Supplier1Service supplier1Service;
    private final Supplier2Service supplier2Service;

    public ProductController(ProductService productService, Supplier1Service supplier1Service, Supplier2Service supplier2Service) {
        this.productService = productService;
        this.supplier1Service = supplier1Service;
        this.supplier2Service = supplier2Service;

        Thread Trans = new Thread(this);
        Trans.setPriority(Thread.MIN_PRIORITY);
        Trans.start();
    }

    @GetMapping("/catalog")
    public UniversalProduct[] getAllByFilter(@RequestParam(name = "category", defaultValue = "default") String category,
                                    @RequestParam(name = "minPrice", defaultValue = "0") int minPrice,
                                    @RequestParam(name = "maxPrice", defaultValue = "2147483647") int maxPrice) {

        List<Product> mainProductList;
        List<Supplier1Product> supplier1ProductList;
        List<Supplier2Product> supplier2ProductList;

        if (category.equalsIgnoreCase("default")) {
            mainProductList = productService.readByPrice(minPrice, maxPrice);
            supplier1ProductList = supplier1Service.readByPrice(minPrice, maxPrice);
            supplier2ProductList = supplier2Service.readByPrice(minPrice, maxPrice);

        } else {
            mainProductList = productService.readByCategoryAndPrice(category, minPrice, maxPrice);
            supplier1ProductList = supplier1Service.readByCategoryAndPrice(category, minPrice, maxPrice);
            supplier2ProductList = supplier2Service.readByCategoryAndPrice(category, minPrice, maxPrice);
        }

        List<UniversalProduct> productList = new ArrayList<>();
        productList.addAll(mainProductList);
        productList.addAll(supplier1ProductList);
        productList.addAll(supplier2ProductList);
        return productList.toArray(new UniversalProduct[0]);
    }

    @PostMapping
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @PutMapping("/{productId}")
    public void update(@RequestBody Product product, @PathVariable long productId) {
        productService.update(product, productId);
    }

    @DeleteMapping("/{productId}")
    public void delete(@PathVariable long productId) {
        productService.delete(productId);
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            int oneDayInMilliseconds = 1000 * 60 * 60 * 24;
            Thread.sleep(oneDayInMilliseconds);
            List<Supplier1Product> supplier1ProductList = supplier1Service.catchDataFromSupplier1();
            for (Supplier1Product product : supplier1ProductList) {
                supplier1Service.updateOrCreate(product, product.getProductId());
            }
            List<Supplier2Product> supplier2ProductList = supplier2Service.catchDataFromSupplier2();
            for (Supplier2Product product : supplier2ProductList) {
                supplier2Service.updateOrCreate(product, product.getProductId());
            }
        }
    }

}
