package com.lirugo.print_service.repo;

import com.lirugo.print_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByIdDesc();
}
