package spd.trello.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayMigrator implements InitializingBean {

	private final DataSource dataSource;

	@Autowired
	public FlywayMigrator(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		Flyway flyway = Flyway.configure()
				.locations("classpath:migrations")
				.dataSource(dataSource)
				.load();
		flyway.migrate();
	}
}
