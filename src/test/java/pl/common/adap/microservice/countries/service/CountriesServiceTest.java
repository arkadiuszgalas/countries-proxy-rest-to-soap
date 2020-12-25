package pl.common.adap.microservice.countries.service;

import com.proxy.countries.europe.GetCountryRequestItems;
import org.junit.Test;
import pl.common.adap.microservice.countries.client.CountriesWebServiceClient;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CountriesServiceTest {

    @Test
    public void shouldReceivedCountryItems() {

        // given
        CountriesWebServiceClient client = mock(CountriesWebServiceClient.class);
        GetCountryRequestItems request = mock(GetCountryRequestItems.class);
        CountriesService service = new CountriesService(client);

        // when
        service.getCountry(request);

        // then
        verify(client).getCountries(eq(request));
    }
}