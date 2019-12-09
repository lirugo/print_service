package com.lirugo.print_service.repo;

import com.lirugo.print_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM ordr ORDER BY id DESC LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Order> findAll(int limit, int offset);
}
