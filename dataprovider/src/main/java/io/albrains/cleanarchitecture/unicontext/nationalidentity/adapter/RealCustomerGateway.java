package io.albrains.cleanarchitecture.unicontext.nationalidentity.adapter;

import io.albrains.cleanarchitecture.unicontext.core.application.port.output.CustomerGateway;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.NationalIdentity;
import io.albrains.cleanarchitecture.unicontext.nationalidentity.model.CustomerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RealCustomerGateway implements CustomerGateway {
    private static final String PATH = "customers/%s";

    private final String uri;

    public RealCustomerGateway(@Value("${customer-provider-uri}") final String uri) {
        this.uri = uri;
    }

    @Override
    public boolean isBlacklisted(NationalIdentity nationalIdentity) {
        var client = WebClient.create(uri);
        var path = getPath(nationalIdentity.getIdNumber());
        var responseSpec = client.get().uri(path).retrieve();

        var customerDto = responseSpec
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(CustomerDto.class)
                .block();

        if(customerDto == null) {
            return false;
        }

        return customerDto.isBlacklisted();
    }

    private String getPath(String nationalIdentityNumber) {
        return String.format(PATH, nationalIdentityNumber);
    }
}
