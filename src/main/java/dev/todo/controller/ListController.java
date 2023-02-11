package dev.todo.controller;

import dev.todo.dao.impl.ListTasksDAO;
import dev.todo.model.ListTasks;
import dev.todo.util.ListValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ListController {
    @Autowired
    private ListTasksDAO dao;
    @Autowired
    private ListValidator listValidator;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<ListTasks> lists = dao.readAll();
        model.addAttribute("lists", lists);

        return "lists/index";
    }

    @GetMapping("/add")
    public String getFormAdd(@ModelAttribute("listTasks") ListTasks listTasks, Model model) {
        List<ListTasks> values = dao.readAll();
        model.addAttribute("values", values);

        return "lists/addList";
    }

    @PostMapping("/add")
    public String addList(@ModelAttribute("listTasks") @Valid ListTasks listTasks, BindingResult bindingResult,
                          Model model) {
        listValidator.validate(listTasks, bindingResult);

        if (bindingResult.hasErrors()) {
            List<ListTasks> values = dao.readAll();
            model.addAttribute("values", values);

            return "lists/addList";
        }

        dao.create(listTasks);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdit(@PathVariable("id") long id, Model model) {
        ListTasks listTasks = dao.read(id).get();
        model.addAttribute("id", id);
        model.addAttribute("listTasks", listTasks);
        List<ListTasks> values = dao.readAll();
        model.addAttribute("values", values);

        return "lists/editList";
    }

    @PatchMapping("/edit/{id}")
    public String editList(@ModelAttribute("listTasks") @Valid ListTasks listTasks, BindingResult bindingResult,
                           @PathVariable("id") long id, Model model) {
        listValidator.validate(listTasks, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("listTasks", listTasks);
            List<ListTasks> values = dao.readAll();
            model.addAttribute("values", values);

            return "lists/editList";
        }

        dao.update(listTasks, id);

        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteList(@PathVariable("id") long id) {
        dao.delete(id);

        return "redirect:/";
    }
}