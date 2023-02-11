package dev.todo.controller;

import dev.todo.dao.impl.ListTasksDAO;
import dev.todo.dao.impl.TaskDAO;
import dev.todo.model.ListTasks;
import dev.todo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ListTasksDAO listTasksDAO;
    @Autowired
    private TaskDAO taskDAO;

    @GetMapping("/{id}")
    public String getTasks(@PathVariable("id") long id, Model model) {
        ListTasks list = listTasksDAO.read(id).get();
        model.addAttribute("list", list);
        List<Task> tasks = taskDAO.getTasksById(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("id", id);

        return "tasks/showTasks";
    }

    @GetMapping("/add/{id}")
    public String getFormAdd(@ModelAttribute("task") Task task, @PathVariable("id") long id, Model model) {
        ListTasks list = listTasksDAO.read(id).get();
        model.addAttribute("list", list);
        List<Task> tasks = taskDAO.getTasksById(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("id", id);

        return "tasks/addTask";
    }

    @PostMapping("/add/{id}")
    public String addTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult,
                          @PathVariable("id") long id, Model model) {
        if (bindingResult.hasErrors()) {
            ListTasks list = listTasksDAO.read(id).get();
            model.addAttribute("list", list);
            List<Task> tasks = taskDAO.getTasksById(id);
            model.addAttribute("tasks", tasks);
            model.addAttribute("id", id);

            return "tasks/addTask";
        }

        task.setDone(false);
        ListTasks listTasks = listTasksDAO.read(id).get();
        task.setListTasks(listTasks);

        taskDAO.create(task);
        return "redirect:/tasks/" + id;
    }

    @PatchMapping("{idList}/done/{idTask}")
    public String statusChangeTask(@PathVariable("idList") long idList, @PathVariable("idTask") long idTask) {
        Task task = taskDAO.read(idTask).get();
        boolean status = !task.isDone();
        taskDAO.statusChange(status, idTask);

        return "redirect:/tasks/" + idList;
    }

    @DeleteMapping("{idList}/delete/{idTask}")
    public String deleteTask(@PathVariable("idList") long idList, @PathVariable("idTask") long idTask) {
        taskDAO.delete(idTask);

        return "redirect:/tasks/" + idList;
    }
}
