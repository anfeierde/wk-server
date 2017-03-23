package com.cz1.todo;

import com.cz1.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wkchen on 2017/3/21.
 */
@RestController
@RequestMapping("/todos")
@PreAuthorize("hasRole('USER')")
public class TodoController {

    @Autowired
    private MongoTodoServiceImpl service;

    /**
     * 新增Todo
     * @param todo
     * @return
     */
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
       return service.addTodo(todo);
    }

    /**
     * 查询Todo列表
     * @param username
     * @return
     */
    @GetMapping
    public List<Todo> getTodoList(@RequestHeader("username")String username) {
        return service.findall(username);
    }


    /**
     * 根据id查询Todo
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Todo getTodo(@PathVariable("id") String id) {
        return service.findById(id);
    }

    /**
     * 更新Todo
     * @param id
     * @param todo
     * @return
     */
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable("id") String id,@RequestBody Todo todo) {
        return service.update(id, todo);
    }

    /**
     * 删除Todo
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable("id") String id) {
        return service.deleteTodo(id);
    }
}
