package ru.sefiros.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sefiros.model.Word;

import java.util.List;
import java.util.Random;

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

    public void save(Word word) {
        jdbcTemplate.update("INSERT INTO Word(expression, translation, translatedToRussianCount, translatedToEnglishCount) VALUES(?,?,?,?)",
                word.getExpression(),
                word.getTranslation(),
                word.getTranslatedToRussianCount(),
                word.getTranslatedToEnglishCount());
    }

    public void update(int id, Word word) {
        jdbcTemplate.update("UPDATE Word SET expression=?, translation=? WHERE id=?",
                word.getExpression(), word.getTranslation(), id);
    }

    public void updateLearnCount(int id, Word word) {
        jdbcTemplate.update("UPDATE Word SET translatedToRussianCount=?, translatedToEnglishCount=? WHERE id=?",
                word.getTranslatedToRussianCount(), word.getTranslatedToEnglishCount(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Word WHERE id=?", id);
    }

    public Word getWordToLearn() {
        List<Word> allwords = jdbcTemplate.query("SELECT * FROM Word", new BeanPropertyRowMapper<>(Word.class));

        Random random = new Random();
        return allwords.get(random.nextInt(allwords.size()));
    }

    public void createPostgresqlTable() {
        jdbcTemplate.update("CREATE TABLE word(id SERIAL PRIMARY KEY NOT NULL," +
                "expression VARCHAR, translation VARCHAR," +
                "translatedToRussianCount INT," +
                "translatedToEnglishCount INT)");
    }
}
