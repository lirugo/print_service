package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.Order;

import java.util.List;

public interface OrderService {
    Order getById(int id);
    List<Order> getAll(int limit, int offset);
    List<Order> getAll();
    void save(Order order);
    void delete(int id);
}
