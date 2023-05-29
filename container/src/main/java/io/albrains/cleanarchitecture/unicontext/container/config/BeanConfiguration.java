package io.albrains.cleanarchitecture.unicontext.container.config;

import io.albrains.cleanarchitecture.unicontext.service.bankaccount.TransfertFundsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    TransfertFundsService transfertFundsService() {
        return new TransfertFundsService();
    }
}
