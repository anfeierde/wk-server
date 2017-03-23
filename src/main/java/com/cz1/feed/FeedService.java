package com.cz1.feed;

import com.cz1.domain.Feed;

import java.util.List;

/**
 * Created by wkchen on 2017/3/23.
 */
public interface FeedService {
    Feed addFeed(Feed feed);

    List<Feed> findAll(String ownwe,String type);
}
