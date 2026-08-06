package com.keto.graphqldemo.resolver;

import com.keto.generated.client.BooksGraphQLQuery;
import com.keto.generated.client.BooksProjectionRoot;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.client.codegen.GraphQLQueryRequest;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FakeBookResolverTest {


    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Autowired
    Faker faker;


    @Test
    public void testAllBooks(){
        var graphqlQuery = new BooksGraphQLQuery.Builder().build();
        var projectionRoot = new BooksProjectionRoot().title().author().name().originCountry().getRoot().released().year();

        var graphqlQueryRequest = new GraphQLQueryRequest(graphqlQuery,projectionRoot).serialize();

        List<String> titles= dgsQueryExecutor.executeAndExtractJsonPath(graphqlQueryRequest,"data.books[*].title");

        assertNotNull(titles);



    }





}