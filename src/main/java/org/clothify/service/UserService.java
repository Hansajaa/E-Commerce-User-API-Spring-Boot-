package org.clothify.service;

import org.clothify.entity.UserEntity;
import org.clothify.model.User;

public interface UserService {
    UserEntity saveUserDetails(User user);
    Boolean existsByUserName(String username);
}
