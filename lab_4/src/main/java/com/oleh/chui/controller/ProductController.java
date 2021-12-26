package com.oleh.chui.controller;

import com.oleh.chui.service.ProductService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
