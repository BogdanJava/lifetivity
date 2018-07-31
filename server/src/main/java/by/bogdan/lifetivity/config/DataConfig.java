package by.bogdan.lifetivity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "by.bogdan.lifetivity.repository.mongo")
public class DataConfig {
}
