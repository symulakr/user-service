package com.symulakr.service.user.service.exception;

public class UserAlreadyExistException extends UserServiceException {

   private static final long serialVersionUID = 4430160594071939815L;

   public UserAlreadyExistException(Long userId) {
      super(String.format("New User should not have Id. Id in request:%s", userId));
   }

}
