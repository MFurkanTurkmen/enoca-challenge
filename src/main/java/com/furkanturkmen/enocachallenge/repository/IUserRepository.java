package com.furkanturkmen.enocachallenge.repository;


import com.furkanturkmen.enocachallenge.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserEntity,String> {
    Optional<UserEntity> findOptionalByAuthId(String authId);
}
