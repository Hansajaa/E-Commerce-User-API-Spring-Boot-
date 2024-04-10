package org.clothify.service;

import org.clothify.entity.UserEntity;
import org.clothify.model.Login;

public interface LoginService {
    void saveLoginDetails(Login login);
}
