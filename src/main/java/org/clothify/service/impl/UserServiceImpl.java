package org.clothify.service.impl;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.LoginEntity;
import org.clothify.entity.UserEntity;
import org.clothify.model.User;
import org.clothify.repository.LoginRepository;
import org.clothify.repository.UserRepository;
import org.clothify.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper mapper;
    final StrongPasswordEncryptor encryptor;

    @Override
    public UserEntity saveUserDetails(User user) {
        UserEntity entity = mapper.map(user, UserEntity.class);
        entity.setPassword(encryptor.encryptPassword(user.getPassword()));

        UserEntity savedEntity = userRepository.save(entity);
        return savedEntity;
    }
}
