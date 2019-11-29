package com.lirugo.print_service.graph_ql;

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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Configuration
public class GraphQLConfig {

    @Autowired
    private UserService userService;

    @Bean
    public GraphQL graphQL(ResourceLoader resourceLoader) {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(getSchema(resourceLoader));

        RuntimeWiring runtimeWiring = newRuntimeWiring()
                .type("Query", builder -> builder.dataFetcher("users", new UserFetcher(userService)))
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private static String getSchema(ResourceLoader resourceLoader) {
        try {
            StringBuilder builder = new StringBuilder();
            InputStream inputStream = resourceLoader.getResource("classpath:schema.graphqls").getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while (bufferedReader.ready()) {
                builder.append(bufferedReader.readLine());
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Schema", e);
        }
    }
}
