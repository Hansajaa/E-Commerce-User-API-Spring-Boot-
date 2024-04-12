package org.clothify.service.impl;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.LoginEntity;
import org.clothify.entity.UserEntity;
import org.clothify.model.Login;
import org.clothify.repository.LoginRepository;
import org.clothify.service.LoginService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    final LoginRepository loginRepository;
    final ModelMapper mapper;
    final StrongPasswordEncryptor encryptor;

    @Override
    public Boolean saveLoginDetails(Login login) {

        LoginEntity entity = mapper.map(login,LoginEntity.class);
        entity.setPassword(encryptor.encryptPassword(login.getPassword()));

        loginRepository.save(entity);
        return true;
    }
}
