package com.akijay.retailstore.repository;

import com.akijay.retailstore.entities.StoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vijay on 10/18/16.
 */
@Repository
public interface StoreRepository extends MongoRepository<StoreEntity, Long> {
    StoreEntity findByStoreName(String storeName);

}
