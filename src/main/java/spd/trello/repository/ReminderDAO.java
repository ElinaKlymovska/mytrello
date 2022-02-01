package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spd.trello.domain.Reminder;

import java.util.List;
import java.util.UUID;
@Component
public class ReminderDAO implements IRepository<Reminder>{
    private final JdbcTemplate jdbcTemplate;

    public ReminderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Reminder> getAll() {
        return jdbcTemplate.query("SELECT * FROM reminder", new BeanPropertyRowMapper<>(Reminder.class));
    }

    public Reminder getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM reminder WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Reminder.class))
                .stream().findFirst().orElse(null);
    }

    public Reminder save(Reminder reminder) {

        jdbcTemplate.update("INSERT INTO reminder(id,start_at,end_at,remind_on,active,created_by,updated_by,created_date,updated_date)" +
                        " VALUES(?,?,?,?,?,?,?,?,?)", reminder.getId(), reminder.getStart(), reminder.getEnd(), reminder.getRemindOn(),reminder.getActtive(),
                reminder.getCreatedBy(), reminder.getUpdatedBy(), reminder.getCreatedDate(), reminder.getUpdatedDate());
        return reminder;
    }

    public Reminder update(UUID id, Reminder reminder) {
        jdbcTemplate.update("UPDATE reminder SET start_at=?, end_at=?, remind_on=?,active=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", reminder.getStart(), reminder.getEnd(), reminder.getRemindOn(),reminder.getActtive(),
                reminder.getCreatedBy(), reminder.getUpdatedBy(), reminder.getCreatedDate(), reminder.getUpdatedDate(), id);
        return reminder;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM reminder WHERE id=?", id);
    }
}
