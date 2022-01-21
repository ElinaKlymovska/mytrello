package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.domain.Attachment;
import spd.trello.config.DataBaseConfiguration;

import java.util.List;
import java.util.UUID;

public class AttachmentDAO implements IRepository<Attachment> {
    private final JdbcTemplate jdbcTemplate;

    public AttachmentDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Attachment> getAll() {
        return jdbcTemplate.query("SELECT * FROM attachment", new BeanPropertyRowMapper<>(Attachment.class));
    }

    public Attachment getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM attachment WHERE id=?",
                        new BeanPropertyRowMapper<>(Attachment.class),id)
                .stream().findFirst().orElse(null);
    }

    public Attachment save(Attachment attachment) {
        jdbcTemplate.update("INSERT INTO attachment(id,name,file,comment_id,card_id," +
                        "created_by,updated_by,created_date,updated_date) VALUES(?,?,?,?,?,?,?,?,?)",
                attachment.getId(), attachment.getName(), attachment.getFile().getPath(),
                attachment.getCommentId(),attachment.getCardId(),
                attachment.getCreatedBy(), attachment.getUpdatedBy(), attachment.getCreatedDate(), attachment.getUpdatedDate());
        return attachment;
    }


    public Attachment update(UUID id, Attachment updatedAttachment) {
        jdbcTemplate.update("UPDATE attachment SET name=?, file=?, " +
                        "created_by=?,updated_by=?,created_date=?,updated_date=? WHERE id=?", updatedAttachment.getName(), updatedAttachment.getFile().getAbsolutePath(),
                updatedAttachment.getCreatedBy(), updatedAttachment.getUpdatedBy(), updatedAttachment.getCreatedDate(), updatedAttachment.getUpdatedDate(), id);
        return updatedAttachment;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM attachment WHERE id=?", id);
    }


}

