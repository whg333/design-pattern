package com.whg.server.v01;

import com.whg.api.User;
import com.whg.api.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User findUser(long id) {
        // TODO: 查询缓存/数据库
        return new User(id, "test", 22);
    }
}
