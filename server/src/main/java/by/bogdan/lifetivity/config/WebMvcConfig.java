package by.bogdan.lifetivity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@PropertySources({
        @PropertySource("classpath:types.properties"),
        @PropertySource("classpath:jwt.properties")
})
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("POST", "PUT", "HEAD", "GET", "OPTIONS", "DELETE")
                .allowedOrigins("http://localhost:4200", "http://192.168.14.233:4200");
    }
}
