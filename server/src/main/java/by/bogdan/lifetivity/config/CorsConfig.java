package by.bogdan.lifetivity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class CorsConfig extends CorsConfiguration {

    @Override
    public void setAllowedOrigins(List<String> allowedOrigins) {
        allowedOrigins.add("http://localhost:4200");
    }
}
