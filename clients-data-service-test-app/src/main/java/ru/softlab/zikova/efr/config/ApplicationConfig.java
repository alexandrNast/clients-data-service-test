package ru.softlab.zikova.efr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.softlab.efr.common.bpm.support.EnableBPM;
import ru.softlab.efr.common.utilities.hibernate.annotations.EnableHibernateJpa;
import ru.softlab.efr.infrastructure.logging.annotations.EnableLogging;
import ru.softlab.efr.infrastructure.transport.annotations.EnableTransport;
import ru.softlab.efr.services.authorization.annotations.EnablePermissionControl;
import ru.softlab.efr.settings.logging.annotations.EnableSettingsClientLoggingConfiguration;

/**
 * Конф. класс приложения
 *
 * @author khudyakov
 * @since 28.07.2017
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "ru.softlab.zikova.efr")
@EnableHibernateJpa(
        repositoryPackages = "ru.softlab.zikova.efr.repositories",
        modelPackages = "ru.softlab.zikova.efr.model")
@EnableTransport(ApplicationConfig.APPLICATION_NAME)
@EnableLogging
@EnableSettingsClientLoggingConfiguration
@EnablePermissionControl
@EnableBPM(serviceName = ApplicationConfig.APPLICATION_NAME, basePath = ApplicationConfig.APPLICATION_PATH)
public class ApplicationConfig {
    public static final String APPLICATION_NAME = "clients-data-service-test-app";
    public static final String PATH_DELIMITER = "/";
    public static final String APPLICATION_PATH = PATH_DELIMITER + APPLICATION_NAME;
}