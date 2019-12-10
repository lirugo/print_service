package com.lirugo.print_service.graph_ql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.graph_ql.filter.OrderFilter;
import com.lirugo.print_service.service.OrderService;

import java.util.List;

public class OrderQuery implements GraphQLQueryResolver {
    private OrderService orderService;

    public OrderQuery(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> orders(OrderFilter orderFilter, int limit, int offset) {
        if(orderFilter != null)
            return orderService.getAllWithFilter(orderFilter);
        else
            return orderService.getAll(limit, offset);
    }

    public int orderCount(OrderFilter orderFilter) {
        if(orderFilter != null)
            return orderService.getAllWithFilter(orderFilter).size();
        else
            return orderService.getAll().size();
    }
}
