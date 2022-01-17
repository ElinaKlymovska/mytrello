package org.spdu;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import spd.trello.config.DataBaseConfiguration;

public abstract class  BaseTest {

	protected static HikariDataSource dataSource;

	@BeforeAll
	public static void init() {
		HikariConfig cfg = new HikariConfig();
		cfg.setPassword("sa");
		cfg.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL");
		cfg.setUsername("sa");
		cfg.setDriverClassName("org.h2.Driver");
		dataSource = new HikariDataSource(cfg);
		DataBaseConfiguration.setDataSource(dataSource);
		Flyway flyway = Flyway.configure()
				.locations("classpath:migrations")
				.dataSource(dataSource)
				.load();
		flyway.migrate();
	}
}
