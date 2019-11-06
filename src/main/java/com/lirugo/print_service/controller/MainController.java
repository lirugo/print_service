package com.lirugo.print_service.controller;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        Optional<User> user = userRepo.findById(authentication.getName());

        model.addAttribute("userData", user.get());
        return "index";
    }
}
