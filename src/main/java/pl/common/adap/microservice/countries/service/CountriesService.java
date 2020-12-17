package pl.common.adap.microservice.countries.service;

import com.proxy.countries.europe.GetCountryRequestItems;
import com.proxy.countries.europe.GetCountryResponseItems;
import org.springframework.stereotype.Service;
import pl.common.adap.microservice.countries.client.CountriesWebServiceClient;

@Service
public class CountriesService {
    //adding final keyword to avoid Intellij warnings
    private final CountriesWebServiceClient countriesServiceClient;

    public CountriesService(CountriesWebServiceClient countriesServiceClient) {
        this.countriesServiceClient = countriesServiceClient;
    }

    public GetCountryResponseItems getCountry(GetCountryRequestItems request) {
        return countriesServiceClient.getCountries(request);
    }
}