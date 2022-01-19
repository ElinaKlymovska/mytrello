package spd.trello.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spd.trello.config.DataBaseConfiguration;
import spd.trello.domain.Member;
import spd.trello.domain.Workspace;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

public class MemberDAO implements IRepository<Member>{
    private final JdbcTemplate jdbcTemplate;

    public MemberDAO() {
        this.jdbcTemplate = new JdbcTemplate(DataBaseConfiguration.getDataSource());
    }

    public List<Member> getAll() {
        return jdbcTemplate.query("SELECT * FROM member", new BeanPropertyRowMapper<>(Member.class));
    }

    public Member getById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM member WHERE id=?",new BeanPropertyRowMapper<>(Member.class),id)
                .stream().findFirst().orElse(null);
    }

    public Member save(Member member) {
        jdbcTemplate.update("INSERT INTO member(id,user_id,role) VALUES(?,?,?)", member.getId(), member.getUser().getId(),
                member.getRole().toString());
        return member;
    }

    public Member update(UUID id, Member updatedMember) {
        jdbcTemplate.update("UPDATE member SET name=?, user_id=?, role=? WHERE id=?", updatedMember.getUser().getId(),
                updatedMember.getRole().toString(), id);
        return updatedMember;
    }

    public void delete(UUID id) {
        jdbcTemplate.update("DELETE FROM member WHERE id=?", id);
    }
}
