package org.clothify.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final ModelMapper mapper;
    private final StrongPasswordEncryptor encryptor;


    @Override
    public Boolean saveLoginDetails(Login login) {

        LoginEntity entity = mapper.map(login,LoginEntity.class);
        entity.setPassword(encryptor.encryptPassword(login.getPassword()));

        loginRepository.save(entity);
        return true;
    }

    @Override
    public Boolean authenticateUser(Login login)throws Exception{
        log.info(login.toString());


        LoginEntity byUserName = loginRepository.findByUserName(login.getUserName());
        boolean isCorrect = encryptor.checkPassword(login.getPassword(), byUserName.getPassword());
        if (isCorrect){
            return true;
        }
        return false;
    }
}
