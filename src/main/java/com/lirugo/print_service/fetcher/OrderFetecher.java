package com.lirugo.print_service.fetcher;

import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.service.OrderService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class OrderFetecher implements DataFetcher<List<Order>> {
    private OrderService orderService;

    public OrderFetecher(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public List<Order> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return orderService.getAll();
    }
}
