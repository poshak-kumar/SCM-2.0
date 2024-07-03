package com.scm.SCM_20.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SCM_20.entities.User;
import com.scm.SCM_20.helpers.ResourceNotFoundException;
import com.scm.SCM_20.repositories.UserRepository;
import com.scm.SCM_20.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void deleteUser(String id) {
        // Delete the specific User from Database
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found...."));
        userRepository.delete(user);
        
    }

    @Override
    public List<User> getAllUsers() {
        // Get all the User from Database
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByID(String id) {
        // Get the User by Id from Database
        return userRepository.findById(id);
    }

    @Override
    public Boolean isUserExist(String id) {
        // Checking if User is exist or not in Database
        User user = userRepository.findById(id).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public Boolean isUserExistByEmail(String email) {
        // Checking by email id if the User is exist or not in database
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public User saveUser(User user) {
        // Save the User on Database

        // After saving the User we generate the random dynamically userId 
        String userId = UUID.randomUUID().toString();  /* We have a call UUID , and in this class we have a static method {randomUUID.toString()} this will generate a random String, and then we will set it and then save */ 

        user.setUserId(userId);

        // Set the Password Encoder

        // Add a default profile when User will created
        
        // Now we save the User
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateUser(User user) { // This User will ccontain the new information, that i want to change/update in Database
        // Update the User from Database

        // This user2 contains details from Database
        User user2 = userRepository.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found....."));
        // Updating user2 from user
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNum(user.getPhoneNum());
        user2.setAbout(user.getAbout());
        user2.setProfilePicLink(user.getProfilePicLink());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneNumVerified(user.isPhoneNumVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        // Now save the user on database
        User updatedUser = userRepository.save(user2);
        return Optional.ofNullable(updatedUser);
    }
    
}
