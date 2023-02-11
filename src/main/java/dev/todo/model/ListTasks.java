package dev.todo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

/**
 * Класс для описания списка задач
 * @version 1.0
 */
public class ListTasks implements Cloneable {
    // идентификатор
    private long id;
    // наименование
    @NotEmpty(message = "Наименование не может быть пустым")
    @Size(min = 1, max = 100, message = "Наименование должно содержать от 1 до 100 символов")
    private String name;

    /**
     * Конструктор по умолчанию для создания нового объекта типа ListTasks
     */
    public ListTasks() {}

    /**
     * Конструктор для создания нового объекта типа ListTasks
     * @param id - идентификатор
     * @param name - наименование
     */
    public ListTasks(long id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Перегруженный конструктор для создания нового объекта типа ListTasks
     * @param name - наименование
     * @see #ListTasks(long, String)
     */
    public ListTasks(String name) {
        this(0, name);
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

    @Override
    public ListTasks clone() throws CloneNotSupportedException {
        ListTasks listTasks = (ListTasks) super.clone();
        return listTasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ListTasks list = (ListTasks) obj;
        return (id == list.id) &&
                (name == list.name || (name != null && name.equals(list.name)));
    }

    @Override
    public int hashCode() {
        final int CODE = (int)(31 * 1 + id + (name == null ? 0 : name.hashCode()));
        return CODE;
    }

    @Override
    public String toString() {
        final StringJoiner joiner = new StringJoiner(",", this.getClass().getName() + "{", "}");
        String objectToString = joiner.add("id=" + id).add("name=" + name).toString();
        return objectToString;
    }
}