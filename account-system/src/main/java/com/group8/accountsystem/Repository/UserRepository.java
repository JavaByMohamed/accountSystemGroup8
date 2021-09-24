package com.group8.accountsystem.Repository;

import com.group8.accountsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User>findUserById(String userName);

    //List<User> findByNameContaining(String name);

    List<User> findUserByName(String name);

}
