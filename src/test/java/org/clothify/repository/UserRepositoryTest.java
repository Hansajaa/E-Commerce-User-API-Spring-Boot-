package org.clothify.repository;

import org.clothify.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldReturnTrueWhenUsernameIsExists() {
        //given
        UserEntity userEntity = UserEntity.builder()
                .address("Colombo")
                .contactNumber("111111111")
                .email("sample@gmail.com")
                .username("Nilana")
                .password("123")
                .build();

        underTest.save(userEntity);

        //when
        Boolean result = underTest.existsByUsername("Nilana");

        //then
        assertThat(result).isTrue();

    }

    @Test
    void itShouldReturnFalseWhenUsernameIsNotExists() {

        //when
        Boolean result = underTest.existsByUsername("Nilana");

        //then
        assertThat(result).isFalse();

    }
}