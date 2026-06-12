package com.keto.graphqldemo.resolver;


import com.keto.generated.types.Book;
import com.keto.graphqldemo.datasource.FakeBookDataSource;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakeBookResolver {

    @DgsData(parentType = "Query", field = "books")
    public List<Book> bookWrittenBy(@InputArgument(name = "author") Optional<String> authorName){

        if (authorName.isEmpty() || StringUtils.isBlank(authorName.get())){
            return FakeBookDataSource.BOOK_LIST;
        }

        return FakeBookDataSource.BOOK_LIST.stream()
                .filter(b->StringUtils.containsIgnoreCase(b.getAuthor().getName(),authorName.get()))
                .collect(Collectors.toList());

    }


}
