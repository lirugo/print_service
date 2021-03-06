package com.lirugo.print_service.repo;

import com.lirugo.print_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByGoogleId(String googleId);
    User findByEmail(String email);
    User findByName(String name);
}
