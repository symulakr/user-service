package com.symulakr.service.user.service.repository;

import com.symulakr.service.user.service.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {UserRepositoryTest.TestConfiguration.class})
class UserRepositoryTest {

   @Autowired
   UserRepository userRepository;

   @Test
   void shouldSuccessfullyReturnAllUsers() {
      List<User> users = userRepository.findAll();
      assertThat(users).isNotEmpty();
   }

   @Test
   void shouldSuccessfullyCreateNewUser() {
      String userName = UUID.randomUUID().toString();
      User userForCreate = User.builder()
            .name(userName)
            .build();
      User storedUser = userRepository.save(userForCreate);
      Optional<User> actualUser = userRepository.findById(storedUser.getId());
      assertThatUserHasSpecificName(actualUser, userName);

   }

   @Test
   void shouldSuccessfullyUpdateUser() {
      String userName = "John Doe";
      User userForCreate = User.builder()
            .name(userName)
            .build();
      User storedUser = userRepository.save(userForCreate);
      Optional<User> actualUser = userRepository.findById(storedUser.getId());
      assertThatUserHasSpecificName(actualUser, userName);

      String updatedName = "Jane Doe";
      User updatedUser = actualUser.map(User::toBuilder)
            .get()
            .name(updatedName)
            .build();


      userRepository.save(updatedUser);
      actualUser = userRepository.findById(storedUser.getId());
      assertThatUserHasSpecificName(actualUser, updatedName);

   }

   @Test
   void shouldSuccessfullyDeleteUser(){
      String userName = UUID.randomUUID().toString();
      User userForCreate = User.builder()
            .name(userName)
            .build();
      User storedUser = userRepository.save(userForCreate);
      Optional<User> actualUser = userRepository.findById(storedUser.getId());
      assertThatUserHasSpecificName(actualUser, userName);
      userRepository.deleteById(storedUser.getId());
      actualUser = userRepository.findById(storedUser.getId());
      assertThat(actualUser).isEmpty();
   }

   private void assertThatUserHasSpecificName(Optional<User> actualUser, String expectedName){
      assertThat(actualUser).isPresent()
            .map(User::getName)
            .get()
            .isNotNull()
            .isEqualTo(expectedName);
   }

   @Configuration
   @EnableAutoConfiguration
   @EntityScan(basePackageClasses = User.class)
   public static class TestConfiguration {
   }
}