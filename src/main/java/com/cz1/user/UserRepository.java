package com.cz1.user;

import com.cz1.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wkchen on 2017/3/21.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>{
   User findByUsername(String username);
}