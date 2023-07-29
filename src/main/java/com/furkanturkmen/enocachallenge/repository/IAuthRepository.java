package com.furkanturkmen.enocachallenge.repository;

import com.furkanturkmen.enocachallenge.repository.entity.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends MongoRepository<Auth,String> {

    Optional<Auth> findOptionalByMail(String mail);
}
