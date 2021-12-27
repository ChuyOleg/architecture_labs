package com.oleh.chui.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.oleh.chui.model.entity.Person;
import com.oleh.chui.model.entity.Product;
import com.oleh.chui.model.util.Role;
import com.oleh.chui.service.OrderService;
import com.oleh.chui.service.PersonService;
import com.oleh.chui.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductMutation implements GraphQLMutationResolver {

    private final ProductService productService;
    private final PersonService personService;
    private final OrderService orderService;

    public ProductMutation(ProductService productService, PersonService personService, OrderService orderService) {
        this.productService = productService;
        this.personService = personService;
        this.orderService = orderService;
    }

    public Optional<Product> createProduct(String title, String description, String category,
                                          int price, String saleType, String startDate,
                                          String endDate, String deliveryType, int deliveryPrice,
                                          int deliveryTimeDays, Long sellerId) {

        Optional<Person> seller = personService.getPerson(sellerId);

        if (seller.isPresent() && seller.get().getRole().equals(Role.SELLER)) {
            return Optional.of(productService.createProduct(title, description, category, price, saleType,
                    startDate, endDate, deliveryType, deliveryPrice, deliveryTimeDays, seller.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Product> updateProduct(Long productId ,String title, String description, String category,
                                           int price, String saleType, String startDate,
                                           String endDate, String deliveryType, int deliveryPrice,
                                           int deliveryTimeDays, Long sellerId) {

        Optional<Person> seller = sellerId == null ? Optional.empty() : personService.getPerson(sellerId);

        if (seller.isPresent()) {
            return productService.updateProduct(productId, title, description, category, price, saleType, startDate,
                    endDate, deliveryType, deliveryPrice, deliveryTimeDays, seller.get());
        } else {
            return productService.updateProduct(productId, title, description, category, price, saleType, startDate,
                    endDate, deliveryType, deliveryPrice, deliveryTimeDays, null);
        }

    }

    public Optional<Product> deleteProduct(final Long id) {
        Optional<Product> product = productService.getProduct(id);
        productService.deleteProduct(id);
        return product;
    }
}
