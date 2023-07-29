package com.furkanturkmen.enocachallenge.repository;

import com.furkanturkmen.enocachallenge.repository.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends MongoRepository<Product,String> {
    Optional<List<Product>> findOptionalByStoreId(String storeId);
}
