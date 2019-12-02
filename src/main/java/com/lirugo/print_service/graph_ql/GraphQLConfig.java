package com.lirugo.print_service.graph_ql;

import com.lirugo.print_service.graph_ql.directive.DateTimeFormatDirective;
import com.lirugo.print_service.graph_ql.mutatuion.OrderMutation;
import com.lirugo.print_service.graph_ql.query.OrderQuery;
import com.lirugo.print_service.graph_ql.query.UserQuery;
import com.lirugo.print_service.service.OrderService;
import com.lirugo.print_service.service.UserService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class GraphQLConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Bean
    public GraphQL graphQL(ResourceLoader resourceLoader) {
        GraphQLSchema graphQLSchema = buildSchema();

        return GraphQL.newGraphQL(graphQLSchema).build();
    }


    private GraphQLSchema buildSchema() {
        return com.coxautodev.graphql.tools.SchemaParser.newParser()
                .file("schema.graphqls")
                .directive("dateFormat", new DateTimeFormatDirective())
                .resolvers(
                        new UserQuery(userService),
                        new OrderQuery(orderService),
                        new OrderMutation(orderService, userService)
                )
                .build()
                .makeExecutableSchema();
    }
}
