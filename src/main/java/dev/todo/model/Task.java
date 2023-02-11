package dev.todo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

/**
 * Класс для описания задач
 * @version 1.0
 */
public class Task implements Cloneable {
    // идентификатор
    private long id;
    // наименование
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(min = 1, max = 255, message = "Наименование должно содержать от 1 до 255 символов")
    private String name;
    // состояние выполнения задачи
    private boolean done;
    // список, которому принадлежит данная задача
    private ListTasks listTasks;

    /**
     * Конструктор по умолчанию для создания нового объекта типа Task
     */
    public Task() {}
    /**
     * Конструктор для создания нового объекта типа Task
     * @param id - идентификатор
     * @param name - наименование
     * @param done - состояние выполнения задачи
     * @param listTasks - список, которому принадлежит данная задача
     */
    public Task(long id, String name, boolean done, ListTasks listTasks) {
        this.id = id;
        this.name = name;
        this.done = done;
        this.listTasks = listTasks;
    }
    /**
     * Перегруженный конструктор для создания нового объекта типа ListTasks
     * @param name - наименование
     * @param done - состояние выполнения задачи
     * @param listTasks - список, которому принадлежит данная задача
     * @see #Task(long, String, boolean, ListTasks)
     */
    public Task(String name, boolean done, ListTasks listTasks) {
        this(0, name, done, listTasks);
    }

    /* Методы получения и установления значения */
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    public ListTasks getListTasks() {
        return listTasks;
    }
    public void setListTasks(ListTasks listTasks) {
        this.listTasks = listTasks;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task task = (Task) super.clone();
        task.listTasks = listTasks.clone();
        return task;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return (id == task.id) &&
                (name == task.name || (name != null && name.equals(task.name))) && (done == task.done) &&
                (listTasks == task.listTasks || (listTasks != null && listTasks.equals(task.listTasks)));
    }

    @Override
    public int hashCode() {
        final int CODE = (int)(31 * 1 + id + (name == null ? 0 : name.hashCode()) + (done ? 1 : 0) +
                (listTasks == null ? 0 : listTasks.hashCode()));
        return CODE;
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(",", this.getClass().getName() + "{", "}");
        String objectToString = joiner.add("id=" + id).add("name=" + name).add("done=" + done)
                .add("listTasks=" + listTasks).toString();
        return objectToString;
    }
}