package com.keto.graphqldemo.datasource;

import com.keto.generated.types.Address;
import com.keto.generated.types.Author;
import com.keto.generated.types.Book;
import com.keto.generated.types.ReleasedHistory;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeBookDataSource {

    @Autowired
    private Faker faker;

    public static final List<Book> BOOK_LIST=new ArrayList<>();

    @PostConstruct
    public void postConstruct(){

        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .address(addresses)
                    .build();

            var released = ReleasedHistory.newBuilder()
                    .printedEdition(faker.bool().bool())
                    .releasedCountry(faker.country().name())
                    .year(faker.number().numberBetween(2019, 2026))
                    .build();

            var book = Book.newBuilder()
                    .publisher(faker.book().publisher())
                    .author(author)
                    .title(faker.book().title())
                    .released(released)
                    .build();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1,3); j++) {
                var address = Address.newBuilder()
                        .street1(faker.address().streetAddress())
                        .street2(faker.address().streetAddress())
                        .street3(faker.address().streetAddress())
                        .city(faker.address().cityName())
                        .zipCode(faker.address().zipCode())
                        .country(faker.address().country())
                        .build();
                addresses.add(address);
            }


            BOOK_LIST.add(book);
        }


    }



}
