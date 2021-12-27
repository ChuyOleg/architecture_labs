package com.oleh.chui.controller;

import com.oleh.chui.model.Product;
import com.oleh.chui.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("price-list")
    public Product[] getAllWithPagination() {
        return productService.getAll().toArray(new Product[0]);
    }

}
