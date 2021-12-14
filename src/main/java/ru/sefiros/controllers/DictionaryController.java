package ru.sefiros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sefiros.dao.DictionaryDAO;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

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
}
