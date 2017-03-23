package com.cz1.feed;

import com.cz1.domain.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wkchen on 2017/3/23.
 */
@RestController
@RequestMapping("/feeds")
public class FeedController {

    @Autowired
    private FeedServiceImpl service;

    @RequestMapping(value = "/{owner}/{type}",method = RequestMethod.GET)
    public List<Feed> feedList(@PathVariable("owner")String owner,@PathVariable("type")String type) {
        return service.findAll(owner, type);
    }

    @PostMapping
    @PreAuthorize("principal.username.equals(#feed.owner)")
    public Feed newFeed(@RequestBody Feed feed) {
        return service.addFeed(feed);
    }
}
