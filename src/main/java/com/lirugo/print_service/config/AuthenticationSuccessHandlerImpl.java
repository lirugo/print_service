package com.lirugo.print_service.config;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.repo.UserRepo;
import com.lirugo.print_service.service.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        //Update user last visit
        User user = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        user.setLastVisit(LocalDateTime.now());
        userRepo.save(user);

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        try {
            redirectStrategy.sendRedirect(request, response, "/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}