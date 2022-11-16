package ru.softlab.zikova.efr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.softlab.efr.common.utilities.hibernate.annotations.EnableHibernateJpa;
import ru.softlab.efr.test.infrastructure.transport.rest.config.AbstractTestConfiguration;

/**
 * Конфигурационный класс запуска тестов
 *
 * @author khudyakov
 * @since 25.07.2017
 */
@Configuration
@Import({HibernateConfig.class})
@EnableHibernateJpa(
        repositoryPackages = {
                "ru.softlab.zikova.efr.repositories",
                "ru.softlab.efr.services.authorization.repositories"},
        modelPackages = {
                "ru.softlab.zikova.efr.model",
                "$ru.softlab.efr.services.authorization.model"})
public class TestApplicationConfig extends AbstractTestConfiguration {
    private static final String APPLICATION_NAME = "clients-data-service-test-app";

    @Override
    protected String getTestAppName() {
        return APPLICATION_NAME;
    }

    @Override
    protected String getTestAppContextRoot() {
        //context root из jboss-web.xml
        return APPLICATION_NAME;
    }
}