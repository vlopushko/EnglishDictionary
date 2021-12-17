package ru.sefiros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sefiros.dao.DictionaryDAO;
import ru.sefiros.model.Word;

import javax.validation.Valid;

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

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Word person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dictionary/new";
        }
        dictionaryDAO.save(person);
        return "redirect:/dictionary";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", dictionaryDAO.show(id));
        return "dictionary/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("word") @Valid Word word, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "dictionary/edit";
        }
        dictionaryDAO.update(id, word);
        return "redirect:/dictionary";
    }

    //To initialize DB structure, execute /dictionary/createPostgresqlTable GET-request
    @GetMapping("/createPostgresqlTable")
    public String createPostgresqlTable() {
        dictionaryDAO.createPostgresqlTable();
        return "redirect:/dictionary";
    }
}
