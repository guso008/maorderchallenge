package com.ma.pedidos.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.ma.pedidos.repository" })
@PropertySource({ "classpath:application.properties" })
@EnableJpaAuditing
@ComponentScan({ "com.ma.pedidos.repository" })
public class PersistConfig {
}
