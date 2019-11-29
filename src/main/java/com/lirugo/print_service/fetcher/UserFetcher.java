package com.lirugo.print_service.fetcher;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.service.UserService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

public class UserFetcher implements DataFetcher<List<User>> {
    private UserService userService;

    public UserFetcher(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return userService.getAll();
    }
}
