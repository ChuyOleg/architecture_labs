package com.oleh.chui.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.oleh.chui.model.entity.Product;
import com.oleh.chui.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductQuery implements GraphQLQueryResolver {

    private final ProductService productService;

    public ProductQuery(ProductService productService) {
        this.productService = productService;
    }

    public Optional<Product> product(final Long id) {
        return productService.getProduct(id);
    }

    public List<Product> products() {
        return productService.getProducts();
    }
}
