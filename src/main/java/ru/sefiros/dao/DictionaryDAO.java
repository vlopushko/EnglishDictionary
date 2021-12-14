package ru.sefiros.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sefiros.model.Word;

import java.util.List;

@Component
public class DictionaryDAO {

    //http://localhost:8080/dictionary
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DictionaryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Word> index() {
        return jdbcTemplate.query("SELECT * FROM Word", new BeanPropertyRowMapper<>(Word.class));
    }
}
