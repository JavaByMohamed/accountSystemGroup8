package com.group8.accountsystem.Repository;

import com.group8.accountsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User>findUserById(String userName);

}
