package com.cz1.auth;

import com.cz1.domain.User;

/**
 * Created by wkchen on 2017/3/22.
 */
public interface AuthService {
    User register(User user);

    String login(String username, String password);

    String reftrsh(String oldToken);
}
