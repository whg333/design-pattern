package com.whg.server.v09;

import com.whg.api.User;
import com.whg.api.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private static final Map<Long, User> userMap = new HashMap<Long, User>(){
        {
            put(12345L, new User(12345L, "test", 22));
            put(1L, new User(1L, "A", 18));
            put(2L, new User(2L, "B", 30));
        }
    };

    @Override
    public User findUser(long id) {
        // TODO: 查询缓存/数据库
        return userMap.get(id);
    }

    @Override
    public boolean saveUser(User user) {
        System.out.println("保存用戶user="+user+"成功！");
        return true;
    }

}
