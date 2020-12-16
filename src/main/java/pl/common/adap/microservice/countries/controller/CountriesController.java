package pl.common.adap.microservice.countries.controller;

import com.proxy.countries.europe.GetCountryRequestItems;
import com.proxy.countries.europe.GetCountryResponseItems;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.common.adap.microservice.countries.service.CountriesService;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    private CountriesService customerService;

    public CountriesController(CountriesService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/getcountry")
    public GetCountryResponseItems getCountry(@RequestBody GetCountryRequestItems request) {
        return customerService.getCountry(request);
    }
}