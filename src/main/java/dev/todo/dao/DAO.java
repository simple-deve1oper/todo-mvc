package dev.todo.dao;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для описания базовых методов DAO
 * @param <T> - объект, с корым будут работать методы
 * @version 1.0
 */
public interface DAO<T> {
    /**
     * Создание объекта
     * @param entity - объект
     */
    void create(T entity);
    /**
     * Чтение всех объектов
     * @return
     */
    List<T> readAll();
    /**
     * Чтение одного объекта по идентификатору
     * @param id - идентификатор
     * @return возвращает класс-оболочку Optional
     */
    Optional<T> read(long id);
    /**
     * Удаление объекта по его идентификатору
     * @param id - идентификатор
     */
    void delete(long id);
}