package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.User;

import java.util.List;

public interface UserService {
    User getById(int id);
    List<User> getAll();
}
