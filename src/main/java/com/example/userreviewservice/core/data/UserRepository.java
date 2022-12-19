package com.example.userreviewservice.core.data;

import com.example.userreviewservice.core.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    @Query(value = "{_id:  ?0}")
    UserEntity findByUserId(String userId);
}
