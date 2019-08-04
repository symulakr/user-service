package com.symulakr.service.user.service.service;

import com.symulakr.service.user.service.repository.UserRepository;
import com.symulakr.service.user.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

   UserService userService;

   @Mock
   UserRepository userRepository;

   private static final Long USER_ID = 222L;
   private static final User EXPECTED_USER = User.builder()
         .id(USER_ID)
         .name("User NAme")
         .build();
   private static final User NEW_USER = User.builder()
         .name("New User NAme")
         .build();

   @BeforeEach
   void setUp() {
      userService = new UserService(userRepository);
   }

   @Test
   void shouldCallSaveOnRepository() {
      userService.create(NEW_USER);
      verify(userRepository).save(NEW_USER);
   }

   @Test
   void shouldCallFindByIdOnRepository() {
      when(userRepository.findById(USER_ID)).thenReturn(Optional.of(EXPECTED_USER));
      userService.findById(USER_ID);
   }

   @Test
   void shouldCallFindAllOnRepository() {
      userService.findAll();
      verify(userRepository).findAll();
   }

   @Test
   void shouldCallDeleteByOnRepository() {
      userService.deleteById(USER_ID);
      verify(userRepository).deleteById(USER_ID);
   }
}