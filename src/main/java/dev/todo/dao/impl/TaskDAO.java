package dev.todo.dao.impl;

import dev.todo.dao.DAO;
import dev.todo.dao.mapper.TaskMapper;
import dev.todo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Класс для реализации интерфейса DAO
 * @version 1.0
 */
@Component
public class TaskDAO implements DAO<Task> {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void create(Task entity) {
        final String SQL = "INSERT INTO tasks(name, done, list_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(SQL, entity.getName(), entity.isDone(), entity.getListTasks().getId());
    }

    @Override
    public List<Task> readAll() {
        final String SQL = "SELECT * FROM tasks";
        List<Task> tasks = jdbcTemplate.query(SQL, taskMapper);
        return tasks;
    }

    @Override
    public Optional<Task> read(long id) {
        final String SQL = "SELECT * FROM tasks WHERE id = ?";
        Optional<Task> optionalTask = jdbcTemplate.query(SQL, taskMapper, id).stream().findAny();
        return optionalTask;
    }

    @Override
    public void delete(long id) {
        final String SQL = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    /**
     * Получение задач по идентификатору списка задач
     * @param id - идентификатор списка задач
     * @return возвращает задачи по идентификатору списка задач
     */
    public List<Task> getTasksById(long id) {
        final String SQL = "SELECT * FROM tasks WHERE list_id = ?";
        List<Task> tasks = jdbcTemplate.query(SQL, taskMapper, id);
        return tasks;
    }

    /**
     * Меняет состояние задачи
     * @param status - состояние
     * @param id - идентификатор
     */
    public void statusChange(boolean status, long id) {
        final String SQL = "UPDATE tasks SET done = ? WHERE id = ?";
        jdbcTemplate.update(SQL, status, id);
    }
}
