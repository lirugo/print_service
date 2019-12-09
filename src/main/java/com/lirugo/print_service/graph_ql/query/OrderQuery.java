package com.lirugo.print_service.graph_ql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.service.OrderService;

import java.util.List;

public class OrderQuery implements GraphQLQueryResolver {
    private OrderService orderService;

    public OrderQuery(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> orders(int limit, int offset) {
        return orderService.getAll(limit, offset);
    }

    public int orderCount() {
        return orderService.getAll().size();
    }
}
