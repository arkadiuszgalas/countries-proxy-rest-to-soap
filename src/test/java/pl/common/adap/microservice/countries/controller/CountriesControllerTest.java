package pl.common.adap.microservice.countries.controller;

import com.proxy.countries.europe.GetCountryResponseItems;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.common.adap.microservice.countries.service.CountriesService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountriesController.class)
public class CountriesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountriesService countriesService;

    @Test
    public void testGetCountry() throws Exception {

        when(countriesService.getCountry(any())).thenReturn(new GetCountryResponseItems());

        mvc.perform(post("/countries/getcountry")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"isocode\":\"PL\"}"))
                .andExpect(status().isOk());

        verify(countriesService, times(1)).getCountry(any());
        verifyNoMoreInteractions(countriesService);
    }
}