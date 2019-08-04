package com.symulakr.service.user.service.web;

import com.symulakr.service.user.service.exception.UserServiceException;
import com.symulakr.service.user.service.model.User;
import com.symulakr.service.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;

   @GetMapping
   public ResponseEntity<List<User>> findAll() {
      return ResponseEntity.ok(userService.findAll());
   }

   @PostMapping
   public ResponseEntity create(@Valid @RequestBody User user) {
      return ResponseEntity.ok(userService.create(user));
   }

   @GetMapping("/{id}")
   public ResponseEntity<User> findById(@PathVariable Long id) {
      return ResponseEntity.ok(userService.findById(id));
   }

   @PutMapping("/{id}")
   public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
      return ResponseEntity.ok(userService.update(id, user));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity delete(@PathVariable Long id) {
      userService.deleteById(id);
      return ResponseEntity.ok().build();
   }

   @ExceptionHandler
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String handleUserServiceException(UserServiceException e) {
      return logAndFormatExceptionMessage("Bad request", e);
   }

   @ExceptionHandler
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public String handleException(Exception e) {
      return logAndFormatExceptionMessage("Unexpected exception occurred", e);
   }

   private String logAndFormatExceptionMessage(String message, Exception e) {
      message = String.format("%s: %s, \nCause: %s", message, e.getMessage(), getRootCauseMessage(e));
      log.error(message, e);
      return message;
   }

}
