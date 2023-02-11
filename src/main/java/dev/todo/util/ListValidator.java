package dev.todo.util;

import dev.todo.dao.impl.ListTasksDAO;
import dev.todo.model.ListTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Класс, реализующий интерфейс Validator для сравнения наименования объектов типа ListTasks
 */
@Component
public class ListValidator implements Validator {
    @Autowired
    private ListTasksDAO listTasksDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return ListTasks.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ListTasks list = (ListTasks) target;

        if (listTasksDAO.read(list.getName()).isPresent()) {
            errors.rejectValue("name", "", "Это наименование списка уже используется!");
        }
    }
}
