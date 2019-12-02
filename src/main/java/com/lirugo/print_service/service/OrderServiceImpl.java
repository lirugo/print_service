package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.Order;
import com.lirugo.print_service.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepo.deleteById(id);
    }
}
