package org.clothify.configuration;

import org.clothify.service.impl.LoginServiceImpl;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ModelMapper config(){
        return new ModelMapper();
    }

    @Bean
    public StrongPasswordEncryptor setUp(){
        return new StrongPasswordEncryptor();
    }
}
