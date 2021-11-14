package com.whg.api;

public interface UserService {

    User findUser(long id);

    default boolean saveUser(User user){
        throw new UnsupportedOperationException("UserService.saveUser");
    }

}
