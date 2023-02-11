package dev.todo.dao.mapper;

import dev.todo.dao.impl.ListTasksDAO;
import dev.todo.model.ListTasks;
import dev.todo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс, реализующий интерфейс RowMapper для объекта типа Task
 * @version 1.0
 */
@Component
public class TaskMapper implements RowMapper<Task> {
    @Autowired
    private ListTasksDAO listTasksDAO;

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        boolean done = rs.getBoolean("done");
        long idListTasks = rs.getLong("list_id");
        ListTasks listTasks = listTasksDAO.read(idListTasks).get();

        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDone(done);
        task.setListTasks(listTasks);

        return task;
    }
}
