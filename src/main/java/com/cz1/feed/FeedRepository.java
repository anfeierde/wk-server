package com.cz1.feed;

import com.cz1.domain.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wkchen on 2017/3/23.
 */
@Repository
public interface FeedRepository extends MongoRepository<Feed,String> {
    List<Feed> findByOwnerAndType(String owner,String type);
}
