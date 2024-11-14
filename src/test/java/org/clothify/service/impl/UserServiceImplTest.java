package org.clothify.service.impl;


import org.clothify.entity.UserEntity;
import org.clothify.model.User;
import org.clothify.repository.UserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock UserRepository userRepository;
    @Mock ModelMapper mapper;
    @Mock StrongPasswordEncryptor encryptor;
    @InjectMocks UserServiceImpl underTest;

    @Test
    void itShouldReturnSavedUserEntityWhenPassValidUserObject() {
        //given
        User givenUser = User.builder()
                .username("Nilana")
                .password("1234")
                .address("Colombo")
                .contactNumber("000000000")
                .email("sample@gmail.com")
                .build();

        //User Entity before password encryption
        UserEntity userEntity1 = UserEntity.builder()
                .id(01L)
                .username("Nilana")
                .password("1234")
                .address("Colombo")
                .contactNumber("000000000")
                .email("sample@gmail.com")
                .build();

        String encryptedPassword = "hhsu167121h2jajsasasaskjaks";

        UserEntity userEntity2 = UserEntity.builder()
                .id(01L)
                .username("Nilana")
                .password(encryptedPassword)
                .address("Colombo")
                .contactNumber("000000000")
                .email("sample@gmail.com")
                .build();

        when(mapper.map(givenUser,UserEntity.class)).thenReturn(userEntity1);
        when(encryptor.encryptPassword("1234")).thenReturn(encryptedPassword);
        when(userRepository.save(userEntity2)).thenReturn(userEntity2);

        //when
        UserEntity savedUser = underTest.saveUserDetails(givenUser);

        //then
        ArgumentCaptor<UserEntity> argumentCaptor =
                ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(argumentCaptor.capture());

        UserEntity capturedvalue = argumentCaptor.getValue();

        assertThat(capturedvalue).isEqualTo(userEntity2);
        assertThat(savedUser).isEqualTo(userEntity2);
    }

    @Test
    void itShouldReturnTrueWhenPassExistsUsername() {
        //given
        String userName = "Nilana"; // Nilana is Exists in the database

        when(userRepository.existsByUsername(userName)).thenReturn(true);

        //when
        Boolean isExists = underTest.existsByUserName(userName);

        //then
        assertThat(isExists).isTrue();
    }

    @Test
    void itShouldReturnFalseWhenPassUsernameIsNotExists() {
        //given
        String userName = "Hansaja"; // Hansaja is not Exists in the database

        when(userRepository.existsByUsername(userName)).thenReturn(false);

        //when
        Boolean isExists = underTest.existsByUserName(userName);

        //then
        assertThat(isExists).isFalse();
    }

    @Test
    void itShouldThrowsNullPointExceptionWhenParsingNullValuesToParameter(){
        assertThatThrownBy(()-> underTest.saveUserDetails(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("User cannot be null");
    }
}