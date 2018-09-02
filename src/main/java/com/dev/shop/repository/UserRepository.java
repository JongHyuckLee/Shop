package com.dev.shop.repository;

import com.dev.shop.mongo.Users;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<Users, String>{

    <S extends Users> S findByUserId(String userId);

    boolean existsByUserId(String userId);
    boolean existsByUserPassword(String userPassword);
}
