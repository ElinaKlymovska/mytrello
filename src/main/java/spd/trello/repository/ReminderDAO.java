package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.Reminder;
import spd.trello.domain.Workspace;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class ReminderDAO implements IRepository<Reminder>{
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    public ReminderDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
        this.dataSource = dataSource;
    }

    public List<Reminder> getAll() {
        return jdbcTemplate.query("SELECT * FROM reminder", new BeanPropertyRowMapper<>(Reminder.class));
    }

    public Reminder getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM reminder WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Reminder.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Reminder reminder) {

        jdbcTemplate.update("INSERT INTO reminder(id,start_at,end_at,remind_on,active,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?)", reminder.getId(), reminder.getStart(), reminder.getEnd(), reminder.getRemindOn(),reminder.getActtive(),
                reminder.getCreatedBy(), reminder.getUpdatedBy(), reminder.getCreatedDate(), reminder.getUpdatedDate());
    }

    public void update(UUID id, Reminder reminder) {
        jdbcTemplate.update("UPDATE reminder SET start_at=?, end_at=?, remind_on=?,active=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", reminder.getStart(), reminder.getEnd(), reminder.getRemindOn(),reminder.getActtive(),
                reminder.getCreatedBy(), reminder.getUpdatedBy(), reminder.getCreatedDate(), reminder.getUpdatedDate(), id);
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM reminder WHERE id=?", id);
    }
}
