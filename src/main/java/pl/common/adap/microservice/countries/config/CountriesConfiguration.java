package pl.common.adap.microservice.countries.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import pl.common.adap.microservice.countries.client.CountriesWebServiceClient;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import static org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

@Configuration
public class CountriesConfiguration {

    @Value("${pl.common.adap.microservice.countries.client.getcountry.endpoint.url}")
    private String getcountryEndpointUrl;

    @Value("${pl.common.adap.microservice.countries.client.getcountry.connection.timeout}")
    private Integer getcountryConnectionTimeout;

    @Value("${pl.common.adap.microservice.countries.client.getcountry.read.timeout}")
    private Integer getcountryReadTimeout;

    @Bean
    public CountriesWebServiceClient countriesWebServiceClient() throws CertificateException,IOException,KeyManagementException,KeyStoreException,NoSuchAlgorithmException {
        CountriesWebServiceClient client = new CountriesWebServiceClient();
        client.setMarshaller(getcountryMarshaller());
        client.setUnmarshaller(getcountryMarshaller());
        client.setDefaultUri(getcountryEndpointUrl);
        client.setMessageSender(getcountryMessageSender());
        return client;
    }

    @Bean
    public HttpComponentsMessageSender getcountryMessageSender() throws CertificateException,IOException,KeyManagementException,KeyStoreException,NoSuchAlgorithmException {
        HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
        httpComponentsMessageSender.setConnectionTimeout(getcountryConnectionTimeout);
        httpComponentsMessageSender.setReadTimeout((getcountryReadTimeout));
        httpComponentsMessageSender.setHttpClient(httpClient());
        return httpComponentsMessageSender;
    }

    private HttpClient httpClient() throws CertificateException, KeyManagementException, KeyStoreException, IOException, NoSuchAlgorithmException {
        return HttpClientBuilder.create().setSSLSocketFactory(sslSocketFactory())
                .addInterceptorFirst(new RemoveSoapHeadersInterceptor()).build();
    }

    private LayeredConnectionSocketFactory sslSocketFactory() throws CertificateException, KeyManagementException, KeyStoreException, IOException, NoSuchAlgorithmException {
        return new SSLConnectionSocketFactory(sslContext());
    }

    private SSLContext sslContext() throws CertificateException, KeyManagementException, KeyStoreException, IOException, NoSuchAlgorithmException {
        return SSLContextBuilder.create().build();
    }

    private Jaxb2Marshaller getcountryMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.proxy.countries.europe");
        return marshaller ;
    }
}