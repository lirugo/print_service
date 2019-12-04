package com.lirugo.print_service.controller;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.repo.UserRepo;
import com.lirugo.print_service.service.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        model.addAttribute("userData", user);
        return "index";
    }
}
