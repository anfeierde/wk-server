package com.cz1.todo;

import com.cz1.domain.Todo;

import java.util.List;

/**
 * Created by wkchen on 2017/3/21.
 */
public interface TodoService  {

    Todo addTodo(Todo todo);

    List<Todo> findall(String username);

    Todo findById(String id);

    Todo deleteTodo(String id);

    Todo update(String id,Todo todo);
}
