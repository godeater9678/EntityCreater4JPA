package com.jpa.entity.generator;

import com.jpa.entity.generator.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication implements CommandLineRunner {

    @Autowired
    EntityService entityService;

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }


    /**
     * JPA  Entity 클래스를 자동으로 생성합니다.
      * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        entityService.genEntityClasses();
        System.out.println("generate complete");
    }
}
