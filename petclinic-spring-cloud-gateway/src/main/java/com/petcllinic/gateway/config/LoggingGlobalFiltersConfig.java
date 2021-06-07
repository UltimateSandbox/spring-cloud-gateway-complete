package com.petcllinic.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class LoggingGlobalFiltersConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoggingGlobalFiltersConfig.class.getName());

    @Bean
    public GlobalFilter postGlobalFilter() {
        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() ->
                        logger.info("Global Post Filter executed: " + exchange.getRequest().getURI()))));
    }
}
