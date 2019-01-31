package com.ing.kristoffer.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.ing.kristoffer"})
@EnableJpaRepositories("com.ing.kristoffer") 
@EntityScan(basePackageClasses = {INGRestfulApplication.class, Jsr310JpaConverters.class}, basePackages = "com.ing.kristoffer")
public class INGRestfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(INGRestfulApplication.class, args);
    }
}
