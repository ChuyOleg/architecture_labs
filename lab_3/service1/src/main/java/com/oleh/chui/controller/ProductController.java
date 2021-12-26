package com.oleh.chui.controller;

import com.oleh.chui.model.Product;
import com.oleh.chui.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    @ResponseBody
    public Product[] readByFilter(@RequestParam(name = "category", defaultValue = "default") String category,
                                        @RequestParam(name = "minPrice", defaultValue = "0") int minPrice,
                                        @RequestParam(name = "maxPrice", defaultValue = "2147483647") int maxPrice) {
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (category.equalsIgnoreCase("default")) {
            return productService.readByPrice(minPrice, maxPrice).toArray(new Product[0]);
        } else {
            return productService.readByCategoryAndPrice(category, minPrice, maxPrice).toArray(new Product[0]);
        }
    }

}
