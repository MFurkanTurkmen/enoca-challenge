package com.furkanturkmen.enocachallenge.repository;

import com.furkanturkmen.enocachallenge.repository.entity.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStoreRepository extends MongoRepository<Store,String> {
    Optional<Store> findOptionalByAuthId(String authId);
}
