package com.oleh.chui.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.oleh.chui.model.entity.Order;
import com.oleh.chui.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderQuery implements GraphQLQueryResolver {

    private final OrderService orderService;

    public OrderQuery(OrderService orderService) {
        this.orderService = orderService;
    }

    public Optional<Order> order(final Long id) {
        return orderService.getOrder(id);
    }

    public List<Order> orders() {
        return orderService.getOrders();
    }
}
