package com.github.andreepdias.mymoney.config;

import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.service.CategoryService;
import com.github.andreepdias.mymoney.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateDatabase();
        return true;
    }
}
