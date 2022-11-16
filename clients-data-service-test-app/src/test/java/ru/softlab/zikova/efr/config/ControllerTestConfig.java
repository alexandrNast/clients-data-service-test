package ru.softlab.zikova.efr.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.common.utilities.rest.annotations.EnableRestDataConverters;
import ru.softlab.efr.infrastructure.logging.api.services.OperationLogService;
import ru.softlab.efr.test.infrastructure.logging.services.settings.MockOperationLogService;
import ru.softlab.zikova.efr.repositories.ClientRepository;
import ru.softlab.zikova.efr.repositories.ProductRepository;
import ru.softlab.zikova.efr.services.ClientService;
import ru.softlab.zikova.efr.services.ProductService;

/**
 * @author zykova
 * @since 28.04.2021
 */
@Configuration
@EnableRestDataConverters
@EnableSpringDataWebSupport
@EnableWebMvc
@ComponentScan({"ru.softlab.zikova.efr.controllers", "ru.softlab.zikova.efr.api"})
public class ControllerTestConfig {
    @Bean
    public ClientService clientService() {
        return Mockito.mock(ClientService.class);
    }

    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }

    @Bean
    public ClientRepository clientRepository() {return Mockito.mock(ClientRepository.class);}

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public OperationLogService operationLogService() {
        return MockOperationLogService.INSTANCE;
    }
}
