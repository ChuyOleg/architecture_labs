package com.oleh.chui.controller;

import com.oleh.chui.model.Product;
import com.oleh.chui.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/details/{id}")
    public Product details(@PathVariable("id") Long id) {
        return productService.readDetails(id);
    }

    @GetMapping("price-list/page/{num}")
    public Product[] getAllWithPagination(@PathVariable("num") int num) {
        return productService.findProductsWithPagination(num).toArray(new Product[0]);
    }

//    @GetMapping("/generate")
//    public void generateRandomProducts(@RequestParam(name = "count", defaultValue = "0") int count) {
//        productService.generateRandomProducts(count);
//    }
}
