package com.example.webshopdat21b.config;

import com.example.webshopdat21b.utility.ConnectionManagerSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class ConnectionManagerConfig {
    @Configuration
    public class ScopesConfig {

        @Bean
        @Scope("singleton")
        public ConnectionManagerSingleton connectionManagerSingleton() {
            return new ConnectionManagerSingleton();
        }
    }
}
