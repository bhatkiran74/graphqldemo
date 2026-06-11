package com.keto.graphqldemo.resolver;


import com.keto.generated.types.Hello;
import com.keto.graphqldemo.datasource.FakeHelloDataSource;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;

@DgsComponent
public class FakeHelloResolver {

    @DgsQuery
    public List<Hello> allHellos(){
    return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsQuery
    public Hello oneHello(){
        return FakeHelloDataSource.HELLO_LIST.get(0);
    }

}
