package org.clothify.service.impl;

import lombok.RequiredArgsConstructor;
import org.clothify.entity.LoginEntity;
import org.clothify.entity.UserEntity;
import org.clothify.model.User;
import org.clothify.repository.LoginRepository;
import org.clothify.repository.UserRepository;
import org.clothify.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ModelMapper mapper;
    @Override
    public UserEntity saveUserDetails(User user) {
        UserEntity savedEntity = userRepository.save(mapper.map(user, UserEntity.class));
        return savedEntity;
    }
}
