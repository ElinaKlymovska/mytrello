package spd.trello.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayMigrator implements InitializingBean {

	private final DataSource dataSource;

	public FlywayMigrator() {
		this.dataSource = DataBaseConfiguration.getDataSource();
	}

	@Override
	public void afterPropertiesSet(){
		Flyway flyway = Flyway.configure()
				.locations("classpath:migrations")
				.dataSource(dataSource)
				.load();
		flyway.migrate();
	}
}
