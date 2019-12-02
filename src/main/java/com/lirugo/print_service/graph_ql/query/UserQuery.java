package com.lirugo.print_service.graph_ql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.service.UserService;

import java.util.List;

public class UserQuery implements GraphQLQueryResolver {
    private UserService userService;

    public UserQuery(UserService userService) {
        this.userService = userService;
    }

    public List<User> users() {
        return userService.getAll();
    }
}
