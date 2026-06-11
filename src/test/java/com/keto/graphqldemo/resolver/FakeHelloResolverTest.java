package com.keto.graphqldemo.resolver;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FakeHelloResolverTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void oneHello() {

        var graphqlQuery = """
               {
                     oneHello{
                          text
                          randomNumber
                     }
               
               }
               
                """;

        String text = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.oneHello.text");
        Integer randomNumber = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.oneHello.randomNumber");

        assertNotNull(randomNumber);
        assertNotNull(text);

    }


    @Test
    void allHellos() {
        var graphqlQuery = """
               {
                     allHellos{
                          text
                          randomNumber
                     }
               
               }
         
                """;


        List<String> texts = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.allHellos[*].text");
        List<Integer> randomNumbers = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.allHellos[*].randomNumber");

        assertNotNull(randomNumbers);
        assertNotNull(texts);

    }
}