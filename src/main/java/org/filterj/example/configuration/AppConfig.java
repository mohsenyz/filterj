package org.filterj.example.configuration;

import org.filterj.api.FilterAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.filterj")
public class AppConfig {

    @Bean
    public FilterAPI getFilterAPI(){
        return FilterAPI.init();
    }

}
