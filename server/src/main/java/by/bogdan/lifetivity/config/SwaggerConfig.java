package by.bogdan.lifetivity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket applicationAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("by.bogdan.lifetivity.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo(
                "Lifetivity API ",
                "API for remote clients",
                "0.0.1-SNAPSHOT",
                "Terms of Service",
                new Contact("Bogdan Shishkin",
                        "https://github.com/BogdanJava",
                        "bogdanshishkin1998@gmail.com"),
                "UNLICENSE",
                "https://unlicense.org/",
                Arrays.asList(new StringVendorExtension("usage", "free"),
                        new StringVendorExtension("bsuir", "только " +
                                "попробуй показать это в бгуире))0"))
        );
    }

}
