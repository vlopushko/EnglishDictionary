package ru.sefiros.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sefiros.model.Word;

import java.util.List;

@Component
public class DictionaryDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DictionaryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Word> index() {
        return jdbcTemplate.query("SELECT * FROM Word", new BeanPropertyRowMapper<>(Word.class));
    }

    public Word show(int id) {
        return jdbcTemplate.query("SELECT * FROM Word WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Word.class)).stream().findAny().orElse(null);
    }

    public void save(Word person) {
        jdbcTemplate.update("INSERT INTO Person VALUES(1,?,?)", person.getExpression(),
                person.getTranslation());
    }
}
