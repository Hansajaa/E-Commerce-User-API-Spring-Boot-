package org.clothify.service.impl;

import org.clothify.entity.LoginEntity;
import org.clothify.model.Login;
import org.clothify.repository.LoginRepository;
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
class LoginServiceImplTest {

    @Mock LoginRepository loginRepository;
    @Mock StrongPasswordEncryptor encryptor;
    @Mock ModelMapper mapper;
    @InjectMocks  LoginServiceImpl underTest;


    @Test
    void itShouldSaveLoginEntityAndReturnTrue() {
        //given
        Login givenObj = Login.builder()
                .userName("Hansaja")
                .password("1234")
                .build();

        //expected before encrypted
        LoginEntity expectedMappingObj = LoginEntity.builder()
                .userName("Hansaja")
                .password("1234")
                .build();

        //expected after encrypted
        String encryptedPassword = "ajsksjawiquwiq112121nj2n1j12n2j";
        when(encryptor.encryptPassword("1234")).thenReturn(encryptedPassword);
        LoginEntity expectedEncryptedObj = LoginEntity.builder()
                .userName("Hansaja")
                .password(encryptedPassword)
                .build();
        
        when(mapper.map(givenObj, LoginEntity.class)).thenReturn(expectedMappingObj);
        
        //when
        Boolean isSaved = underTest.saveLoginDetails(givenObj);

        //then
        ArgumentCaptor<LoginEntity> argumentCaptor =
                ArgumentCaptor.forClass(LoginEntity.class);

        verify(loginRepository).save(argumentCaptor.capture());

        LoginEntity capturedvalue = argumentCaptor.getValue();

        assertThat(capturedvalue).isEqualTo(expectedEncryptedObj);
        assertThat(isSaved).isTrue();
    }

    @Test
    void itShouldThrowsNullPointExceptionWhenPassNullValueToParameter(){

        assertThatThrownBy(()-> underTest.saveLoginDetails(null))
                .isInstanceOf(NullPointerException.class);

    }


    @Test
    void itShouldReturnTrueWhenPassCorrectUsernameAndPassword() throws Exception {
        //given
        Login givenObj = Login.builder()
                .userName("Hansaja")
                .password("1234")
                .build();

        LoginEntity returnObjByUsername = LoginEntity.builder()
                .userName("Hansaja")
                .password("ajsksjawiquwiq112121nj2n1j12n2j")
                .build();

        when(loginRepository.findByUserName(givenObj.getUserName())).thenReturn(returnObjByUsername);
        when(encryptor.checkPassword(givenObj.getPassword(),returnObjByUsername.getPassword())).thenReturn(true);

        //when
        Boolean isAuthenticate = underTest.authenticateUser(givenObj);

        //then
        assertThat(isAuthenticate).isTrue();
    }

    @Test
    void itShouldReturnFalseWhenGivenPasswordIsIncorrect() throws Exception {
        //given
        Login givenObj = Login.builder()
                .userName("Hansaja")
                .password("1234")
                .build();

        LoginEntity returnObjByUsername = LoginEntity.builder()
                .userName("Hansaja")
                .password("ajsksjawiquwiq112121nj2n1j12n2j") //this is not 1234
                .build();

        when(loginRepository.findByUserName(givenObj.getUserName())).thenReturn(returnObjByUsername);
        when(encryptor.checkPassword(givenObj.getPassword(),returnObjByUsername.getPassword())).thenReturn(false);

        //when
        Boolean isAuthenticate = underTest.authenticateUser(givenObj);

        //then
        assertThat(isAuthenticate).isFalse();
    }


    @Test
    void itShouldThrowsExceptionWhenUserNotExistsInDatabase() {
        //given
        Login givenObj = Login.builder()
                .userName("Nilana") //Nilana is not exists in the database
                .password("1234")
                .build();

        when(loginRepository.findByUserName(givenObj.getUserName())).thenReturn(null);

        //when
        //then
        assertThatThrownBy(()->underTest.authenticateUser(givenObj))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("User Not Found");
    }


}