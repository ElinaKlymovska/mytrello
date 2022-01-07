package spd.trello.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfiguration {
    private static DataSource dataSource;

    static {
        try {
            dataSource = createDataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startMigration() {
        Flyway flyway = createFlyway(dataSource);
        flyway.migrate();
    }

    private static Flyway createFlyway(DataSource dataSource) {
        return Flyway.configure().locations("classpath:migrations").dataSource(dataSource).load();
    }

    private static Properties loadProperties() throws IOException {
        InputStream in = DataBaseConfiguration.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(in);
        return properties;
    }

    private static DataSource createDataSource() throws IOException {
        Properties properties = loadProperties();

        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(properties.getProperty("jdbc.url"));
        cfg.setUsername(properties.getProperty("jdbc.username"));
        cfg.setPassword(properties.getProperty("jdbc.password"));

        return new HikariDataSource(cfg);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}