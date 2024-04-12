package org.clothify.service;

import org.clothify.entity.UserEntity;
import org.clothify.model.Login;

public interface LoginService {
    Boolean saveLoginDetails(Login login);

    Boolean authenticateUser(Login login)throws Exception;
}
