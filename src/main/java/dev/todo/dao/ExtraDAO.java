package dev.todo.dao;

/**
 * Интерфейс для описания расширенных методов DAO
 * @param <T> - объект, с корым будут работать методы
 */
public interface ExtraDAO<T> extends DAO<T> {
    /**
     * Обновление объекта
     * @param entity - объект
     * @param id - идентификатор
     */
    void update(T entity, long id);
}