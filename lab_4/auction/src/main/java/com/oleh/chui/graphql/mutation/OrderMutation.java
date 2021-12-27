package com.oleh.chui.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.oleh.chui.model.entity.Order;
import com.oleh.chui.model.entity.Person;
import com.oleh.chui.model.entity.Product;
import com.oleh.chui.model.util.Role;
import com.oleh.chui.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class OrderMutation implements GraphQLMutationResolver {

    private final OrderService orderService;
    private final PersonService personService;
    private final ProductService productService;
    private final Supplier1Service supplier1Service;
    private final Supplier2Service supplier2Service;

    public OrderMutation(OrderService orderService, PersonService personService, ProductService productService, Supplier1Service supplier1Service, Supplier2Service supplier2Service) {
        this.orderService = orderService;
        this.personService = personService;
        this.productService = productService;
        this.supplier1Service = supplier1Service;
        this.supplier2Service = supplier2Service;
    }

    public Optional<Order> createOrder(Long productId, int supplier, Long customerId) {
        Optional<Person> customer = personService.getPerson(customerId);
        Optional<Product> product;
        if (supplier == 0) {
            product = productService.getProduct(productId);
        } else if (supplier == 1) {
            product = getProductFromSupplier1ById(productId);
        } else if (supplier == 2) {
            product = getProductFromSupplier2ById(productId);
        } else {
            product = Optional.empty();
        }

        if (customer.isPresent() && product.isPresent() && customer.get().getRole().equals(Role.CUSTOMER)) {
            product = Optional.ofNullable(productService.createProduct(product.get()));
            Order order = Order.builder()
                    .customer(customer.get()).product(product.get()).
                    dateTime(LocalDate.now()).build();

            return Optional.of(orderService.createOrder(order));
        } else {
            return Optional.empty();
        }

    }

    private Optional<Product> getProductFromSupplier1ById(Long productId) {
        List<Product> productList = supplier1Service.catchDataFromSupplier1();
        Optional<Product> product = productList.stream().filter(p -> Objects.equals(p.getProductId(), productId)).findAny();
        product.ifPresent(p -> p.setSupplier(1));
        product.ifPresent(p -> p.setProductId(0L));
        return product;
    }

    private Optional<Product> getProductFromSupplier2ById(Long productId) {
        List<Product> productList = supplier2Service.catchDataFromSupplier2();
        Optional<Product> product = productList.stream().filter(p -> Objects.equals(p.getProductId(), productId)).findAny();
        product.ifPresent(p -> p.setSupplier(2));
        product.ifPresent(p -> p.setProductId(0L));
        return product;
    }
}
