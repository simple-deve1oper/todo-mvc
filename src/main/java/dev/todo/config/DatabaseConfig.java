package dev.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Класс для настройки Базы Данных
 * @version 1.0
 */
@Configuration
@ComponentScan("cat712.todo")
@EnableWebMvc
@PropertySource("classpath:database.properties")
public class DatabaseConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String driver = environment.getProperty("driver");
        String url = environment.getProperty("url");
        String username = environment.getProperty("username");
        String password = environment.getProperty("password");

        dataSource.setDriverClassName(Objects.requireNonNull(driver));
        dataSource.setUrl(Objects.requireNonNull(url));
        dataSource.setUsername(Objects.requireNonNull(username));
        dataSource.setPassword(Objects.requireNonNull(password));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
