package com.lirugo.print_service.graph_ql.directive;

import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatDirective implements SchemaDirectiveWiring {
    @Override
    public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
        GraphQLFieldDefinition field = environment.getElement();
        //
        // DataFetcherFactories.wrapDataFetcher is a helper to wrap data fetchers so that CompletionStage is handled correctly
        // along with POJOs
        //
        DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(field.getDataFetcher(), ((dataFetchingEnvironment, value) -> {
            DateTimeFormatter dateTimeFormatter = buildFormatter(dataFetchingEnvironment.getArgument("format"));
            if (value instanceof LocalDateTime) {
                return dateTimeFormatter.format((LocalDateTime) value);
            }
            return value;
        }));

        //
        // This will extend the field by adding a new "format" argument to it for the date formatting
        // which allows clients to opt into that as well as wrapping the base data fetcher so it
        // performs the formatting over top of the base values.
        //
        return field.transform(builder -> builder
                .argument(GraphQLArgument
                        .newArgument()
                        .name("format")
                        .type(Scalars.GraphQLString)
                        .defaultValue("dd-MM-YYYY HH:mm:ss")
                )
                .dataFetcher(dataFetcher)
        );
    }

    private DateTimeFormatter buildFormatter(String format) {
        String dtFormat = format != null ? format : "dd-MM-YYYY HH:mm:ss";
        return DateTimeFormatter.ofPattern(dtFormat);
    }
}
