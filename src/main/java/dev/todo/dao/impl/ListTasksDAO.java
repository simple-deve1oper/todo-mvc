package dev.todo.dao.impl;

import dev.todo.dao.ExtraDAO;
import dev.todo.model.ListTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Класс для реализации интерфейса ExtraDAO
 * @version 1.0
 */
@Component
public class ListTasksDAO implements ExtraDAO<ListTasks> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(ListTasks entity) {
        final String SQL = "INSERT INTO lists(name) VALUES (?)";
        jdbcTemplate.update(SQL, entity.getName());
    }

    @Override
    public List<ListTasks> readAll() {
        final String SQL = "SELECT * FROM lists";
        List<ListTasks> lists = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ListTasks.class));
        return lists;
    }

    @Override
    public void delete(long id) {
        final String SQL = "DELETE FROM lists WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public Optional<ListTasks> read(long id) {
        final String SQL = "SELECT * FROM lists WHERE id = ?";
        Optional<ListTasks> optional = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ListTasks.class), id)
                .stream().findAny();
        return optional;
    }

    /**
     * Чтение объекта по его наименованию
     * @param name - наименование
     * @return возвращает класс-оболочку Optional
     */
    public Optional<ListTasks> read(String name) {
        final String SQL = "SELECT * FROM lists WHERE name = ?";
        Optional<ListTasks> optional = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ListTasks.class), name)
                .stream().findAny();
        return optional;
    }

    @Override
    public void update(ListTasks entity, long id) {
        final String SQL = "UPDATE lists SET name = ? WHERE id = ?";
        jdbcTemplate.update(SQL, entity.getName(), id);
    }
}