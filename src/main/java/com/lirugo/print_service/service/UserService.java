package com.lirugo.print_service.service;

import com.lirugo.print_service.entity.User;
import com.lirugo.print_service.repo.UserRepo;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @GraphQLQuery(name = "user")
    public User getById(@GraphQLArgument(name = "id") String id) {
        return userRepo.findById(id).get();
    }
}
