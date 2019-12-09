package com.lirugo.print_service.controller;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.service.UserService;
import com.lirugo.print_service.service.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Transactional
    public String index(Model model){
        User user = userService.getById(((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId());

        model.addAttribute("userData", user);
        return "index";
    }
}
