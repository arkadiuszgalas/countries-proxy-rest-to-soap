package pl.common.adap.microservice.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"pl.common.adap.microservice.countries"})
public class CountriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesApplication.class, args);
    }
}