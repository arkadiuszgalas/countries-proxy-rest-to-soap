package pl.common.adap.microservice.countries.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.proxy.countries.europe.GetCountryRequestItems;
import com.proxy.countries.europe.GetCountryResponseItems;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CountriesWebServiceClient extends WebServiceGatewaySupport {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @HystrixCommand
    public GetCountryResponseItems getCountries(GetCountryRequestItems request) {
        return (GetCountryResponseItems) getWebServiceTemplate().marshalSendAndReceive(
                new JAXBElement<>(new QName(NAMESPACE_URI,"getCountryRequest"),
                                  GetCountryRequestItems.class,
                                  request)
        );
    }
}