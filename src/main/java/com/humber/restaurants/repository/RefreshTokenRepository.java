package com.humber.restaurants.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.humber.restaurants.document.RefreshToken;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    void deleteByOwner_Id(ObjectId id);
    //this method is not shown , but we use such methods where object_property can be used.. owner and itsid
    default void deleteByOwner_Id(String id) {
        deleteByOwner_Id(new ObjectId(id));
    };
    
 
}