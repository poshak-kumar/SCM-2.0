package com.scm.SCM_20.services;

import java.util.List;
import java.util.Optional;

import com.scm.SCM_20.entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserByID(String id);
    Boolean isUserExist(String id);
    Boolean isUserExistByEmail(String email);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    List<User> getAllUsers();

    // Add the more method if needed
}
