package ru.podkovyrov.denis.routiin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.podkovyrov.denis.routiin.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class RoutiinApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutiinApplication.class, args);
    }

}
