package ru.sefiros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sefiros.dao.DictionaryDAO;
import ru.sefiros.model.Word;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

    //http://localhost:8080/dictionary

    private final DictionaryDAO dictionaryDAO;

    @Autowired
    public DictionaryController(DictionaryDAO dictionaryDAO) {
        this.dictionaryDAO = dictionaryDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("words", dictionaryDAO.index());
        return "dictionary/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("word", dictionaryDAO.show(id));
        return "dictionary/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("word") Word word, Model model) {
        return "dictionary/new";
    }
}
