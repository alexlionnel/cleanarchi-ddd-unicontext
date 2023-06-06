package io.albrains.cleanarchitecture.unicontext.nationalidentity.adapter;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.NationalIdentity;
import io.albrains.cleanarchitecture.unicontext.nationalidentity.model.UserDto;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.NationalIdentityGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RealNationalIdentityGateway implements NationalIdentityGateway {

    @Value("${national.identify.url}")
    private String url;
    private static final String PATH = "users/%s";

    public RealNationalIdentityGateway() {
    }

    @Override
    public boolean exists(NationalIdentity nationalIdentity) {
        var client = WebClient.create(url);
        var path = getPath(nationalIdentity.getNumber());
        var responseSpec = client.get().uri(path).retrieve();

        var user = responseSpec
                .onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
                .bodyToMono(UserDto.class)
                .block();

        return user != null && user.getId() != null;
    }

    private String getPath(String nationalIdentityNumber) {
        return String.format(PATH, nationalIdentityNumber);
    }
}
