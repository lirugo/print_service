package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.graph_ql.filter.OrderFilter;
import com.lirugo.print_service.repo.OrderRepo;
import com.lirugo.print_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public Order getById(int id) {
        return orderRepo.findById(id);
    }

    @Override
    public List<Order> getAll(int limit, int offset) {
        return orderRepo.findAll(limit, offset);
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.findAllByOrderByIdDesc();
    }

    @Override
    public List<Order> getAllWithFilter(OrderFilter orderFilter) {
        List<Order> orders;

        if(orderFilter.getAuthorId() != 0){
            User author = userRepo.findById(orderFilter.getAuthorId());
            orders = orderRepo.findByAuthorOrderByIdDesc(author);
        }
        else if(orderFilter.getExecutorId() != 0){
            User executor = userRepo.findById(orderFilter.getExecutorId());
            orders = orderRepo.findByExecutorOrderByIdDesc(executor);
        }else {
            orders = getAll();
        }

        return orders;
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepo.deleteById(id);
    }
}
