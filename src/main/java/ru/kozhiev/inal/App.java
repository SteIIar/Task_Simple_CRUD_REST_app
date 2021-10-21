package ru.kozhiev.inal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.kozhiev.inal.configuration.ApplicationConfiguration;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
