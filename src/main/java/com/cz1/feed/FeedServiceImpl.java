package com.cz1.feed;

import com.cz1.domain.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wkchen on 2017/3/23.
 */
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepository feedRepository;

    @Override
    public Feed addFeed(Feed feed) {
        feed.setPublished_at(new Date());
        feed.setCreated_at(new Date());
        feed.setUpdated_at(new Date());
        return feedRepository.insert(feed);
    }

    @Override
    public List<Feed> findAll(String ownwe,String type) {
        return feedRepository.findByOwnerAndType(ownwe,type);
    }
}
