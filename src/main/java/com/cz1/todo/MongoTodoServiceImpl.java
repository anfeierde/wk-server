package com.cz1.todo;

import com.cz1.domain.Todo;
import com.cz1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wkchen on 2017/3/21.
 */
@Service
public class MongoTodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> findall(String username) {
        return todoRepository.findByUserUsername(username);
    }

    @Override
    public Todo findById(String id) {
        return todoRepository.findOne(id);
    }

    @Override
    public Todo deleteTodo(String id) {
        Todo todo = todoRepository.findOne(id);
        todoRepository.delete(id);
        return todo;
    }

    @Override
    public Todo update(String id,Todo todo) {
        todo.setId(id);
        todoRepository.save(todo);
        return todo;
    }
}
