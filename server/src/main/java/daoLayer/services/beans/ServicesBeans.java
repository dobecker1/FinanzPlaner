package daoLayer.services.beans;

import daoLayer.services.LedgerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = LedgerService.class)
public class ServicesBeans {

    @Bean
    public LedgerService ledgerService() {
        return new LedgerService();
    }
}
