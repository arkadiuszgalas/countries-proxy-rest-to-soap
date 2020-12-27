package pl.common.adap.microservice.countries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CountriesApplication.class)
public class CountriesApplicationTest {

    @Test
    public void contextLoads() {
    }
}