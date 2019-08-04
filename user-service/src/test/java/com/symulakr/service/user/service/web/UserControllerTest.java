package com.symulakr.service.user.service.web;

import com.symulakr.service.user.service.exception.UserNotFoundException;
import com.symulakr.service.user.service.model.User;
import com.symulakr.service.user.service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {

   private static final Long USER_ID = 1L;

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private UserService userService;

   @Test
   void shouldReturnOkOnGetUserById() throws Exception {
      User expected = User.builder()
            .id(USER_ID)
            .name("User Name")
            .build();

      when(userService.findById(USER_ID)).thenReturn(expected);

      performGetUserRequest(USER_ID)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(USER_ID.intValue())));
   }

   @Test
   void shouldReturn500InternalServiceErrorIfAnyExceptionOccurred() throws Exception {
      when(userService.findById(USER_ID)).thenThrow(RuntimeException.class);
      performGetUserRequest(USER_ID)
            .andExpect(status().isInternalServerError());
   }

   @Test
   void shouldReturn400BadRequestUserNotFound() throws Exception {
      when(userService.findById(USER_ID)).thenThrow(UserNotFoundException.class);

      performGetUserRequest(USER_ID)
            .andExpect(status().isBadRequest());
   }

   private ResultActions performGetUserRequest(long userId) throws Exception {
      return mockMvc.perform(get(String.format("/user/%s", userId)));
   }

}