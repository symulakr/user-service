package com.symulakr.service.user.service.service;

import com.symulakr.service.user.service.exception.UserAlreadyExistException;
import com.symulakr.service.user.service.exception.UserNotFoundException;
import com.symulakr.service.user.service.model.User;
import com.symulakr.service.user.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserService {

   private final UserRepository userRepository;

   public List<User> findAll() {
      return userRepository.findAll();
   }

   public User findById(Long id) {
      return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));
   }

   public User create(User user){
      if (Objects.nonNull(user.getId())){
         throw new UserAlreadyExistException(user.getId());
      }
      return userRepository.save(user);
   }

   public User update(Long id, User user){
      findById(id);
      return userRepository.save(user.toBuilder()
            .id(id)
            .build());
   }

   public void deleteById(Long id) {
      try {
         userRepository.deleteById(id);
      } catch (EmptyResultDataAccessException exception) {
         throw new UserNotFoundException(id);
      }
   }

}
