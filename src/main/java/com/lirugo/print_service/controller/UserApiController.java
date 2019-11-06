package com.lirugo.print_service.controller;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/users")
    public List<User> users(){
        return userRepo.findAll();
    }
}
