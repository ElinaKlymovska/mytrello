package spd.trello.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseConfiguration {

    private DataBaseConfiguration() {
    }

    private static DataSource dataSource;

    static {
        try {
            dataSource = createDataSource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DataSource createDataSource() throws IOException {
        Properties properties = loadProperties();

        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(properties.getProperty("jdbc.url"));
        cfg.setUsername(properties.getProperty("jdbc.username"));
        cfg.setPassword(properties.getProperty("jdbc.password"));
        cfg.setDriverClassName("org.postgresql.Driver");
        return new HikariDataSource(cfg);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource1){
        dataSource=dataSource1;
    }

    private static Properties loadProperties() throws IOException {
        InputStream in = DataBaseConfiguration.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        properties.load(in);
        return properties;
    }

}