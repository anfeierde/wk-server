package com.cz1.todo;

import com.cz1.domain.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wkchen on 2017/3/21.
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {
    List<Todo> findByUserUsername(String username);
}
