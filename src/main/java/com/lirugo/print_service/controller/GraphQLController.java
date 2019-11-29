package com.lirugo.print_service.controller;

import com.lirugo.print_service.graph_ql.GraphQLQuery;
import com.lirugo.print_service.graph_ql.GraphQLResponse;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;

    @PostMapping("/graphql")
    public ResponseEntity<GraphQLResponse> graphql(@RequestBody() GraphQLQuery body) {
        ExecutionResult executionResult = graphQL.execute(body.getQuery());
        List<GraphQLError> errors = executionResult.getErrors();
        if (!errors.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(new GraphQLResponse(executionResult.getData()));
    }
}
