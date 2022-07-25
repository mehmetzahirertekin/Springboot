package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.support.SimpleJdbcRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {

        return args -> {
            Student meryem =
                    new Student(
                            "Meryem",
                            "meryem@gmail.com",
                            LocalDate.of(2000, Month.APRIL, 1)
                    );
            Student ali =
                    new Student(
                            "Ali",
                            "ali@gmail.com",
                            LocalDate.of(2004, Month.APRIL, 1)
                    );
            Student ahmet =
                    new Student(
                            "Ahmet",
                            "ahmet@gmail.com",
                            LocalDate.of(2010, Month.APRIL, 1)
                    );

            repository.saveAll(List.of(meryem, ali, ahmet));
        };
    }
}
