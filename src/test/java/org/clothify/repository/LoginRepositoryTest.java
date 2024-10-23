package org.clothify.repository;

import org.clothify.entity.LoginEntity;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;;

@DataJpaTest
public class LoginRepositoryTest {

    @Autowired
    LoginRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldReturnLoginEntityWhenParsingUserNameIsExists() {
        //given
        LoginEntity loginEntity = LoginEntity.builder()
                .userName("Hansaja")
                .password("1234")
                .build();

        underTest.save(loginEntity);

        //when
        LoginEntity givenEntity = underTest.findByUserName("Hansaja");

        //then
        assertThat(givenEntity).isEqualTo(loginEntity);
    }

    @Test
    void itShouldReturnNullWhenParsingUserNameDoesNotExists() {

        //when
        LoginEntity givenEntity = underTest.findByUserName("Hansaja");

        //then
        assertThat(givenEntity).isNull();
    }
}