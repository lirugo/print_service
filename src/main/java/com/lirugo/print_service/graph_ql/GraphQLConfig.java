package com.lirugo.print_service.graph_ql;

import com.lirugo.print_service.graph_ql.directive.DateTimeFormatDirective;
import com.lirugo.print_service.service.UserService;
import com.lirugo.print_service.fetcher.UserFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.*;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Configuration
public class GraphQLConfig {

    @Autowired
    private UserService userService;

    @Bean
    public GraphQL graphQL(ResourceLoader resourceLoader) {
        File schemaFile = null;
        try {
            schemaFile = resourceLoader.getResource("classpath:schema.graphqls").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        
        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .directive("dateFormat", new DateTimeFormatDirective())
                .type("Query", builder -> builder.dataFetcher("users", new UserFetcher(userService)))
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
