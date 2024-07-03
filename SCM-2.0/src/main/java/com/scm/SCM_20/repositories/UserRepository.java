package com.scm.SCM_20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.SCM_20.entities.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,String> {

    // Write the custom finder methods
    
    Optional<User> findByEmail(String email);
}
