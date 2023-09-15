package org.example.util;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseMigration  {
    public void initDb( ) {
        Properties properties = new Properties();
        try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("hibernate.properties")) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String jdbcURL = properties.getProperty("hibernate.connection.url");
        String jdbcUser = properties.getProperty("hibernate.connection.user");
        String jdbcPass = properties.getProperty("hibernate.connection.password");


        Flyway flyway = Flyway.configure()
                .dataSource(jdbcURL, jdbcUser, jdbcPass)
                .load();
        flyway.migrate();
    }
}

