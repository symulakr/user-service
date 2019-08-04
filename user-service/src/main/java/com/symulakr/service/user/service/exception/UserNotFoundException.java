package com.symulakr.service.user.service.exception;

public class UserNotFoundException extends UserServiceException {

   private static final long serialVersionUID = 4430160594071939815L;

   public UserNotFoundException(Long userId) {
      super(String.format("User with id: %s not found.", userId));
   }

}
