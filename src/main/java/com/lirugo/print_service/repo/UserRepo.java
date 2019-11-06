package com.lirugo.print_service.repo;

import com.lirugo.print_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findById(String name);
    User findByEmail(String email);
    User findByName(String name);
}
