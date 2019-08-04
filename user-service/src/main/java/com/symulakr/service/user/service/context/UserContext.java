package com.symulakr.service.user.service.context;

import com.symulakr.service.user.service.repository.UserRepository;
import com.symulakr.service.user.service.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserContext {

   @Bean
   public UserService userService(UserRepository userRepository) {
      return new UserService(userRepository);
   }

}
