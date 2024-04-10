package org.clothify.service.impl;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.LoginEntity;
import org.clothify.entity.UserEntity;
import org.clothify.model.Login;
import org.clothify.repository.LoginRepository;
import org.clothify.service.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    final LoginRepository loginRepository;
    final ModelMapper mapper;
    @Override
    public void saveLoginDetails(Login login) {

        loginRepository.save(mapper.map(login,LoginEntity.class));
    }
}
