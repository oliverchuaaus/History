package com.blueco.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.blueco"})
@EnableMongoRepositories({"com.blueco.repository"})
public class ArticleRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleRestfulApplication.class, args);
    }
}
