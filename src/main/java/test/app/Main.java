package test.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import spd.trello.domain.Card;
import spd.trello.service.BoardService;
import spd.trello.service.CardService;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        new BoardService().print(new BoardService().create());
        CardService cardService = new CardService();
        Card card =cardService.create();
        card.setName("new Name");
        cardService.update(0,card);
        cardService.print(card);

        DataSource dataSource = createDataSource();

        Flyway flyway = createFlyway(dataSource);
        flyway.migrate();
    }

    private static Flyway createFlyway(DataSource dataSource){
       return Flyway.configure().dataSource(dataSource).load();
    }

    private static Properties loadProperties() throws IOException {
        InputStream in = Main.class.getClassLoader().getResourceAsStream("application.properties");
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
}