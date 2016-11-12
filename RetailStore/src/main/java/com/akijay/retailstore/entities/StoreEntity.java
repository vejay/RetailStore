package com.akijay.retailstore.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigInteger;
import java.util.Optional;

/**
 * StoreEntity Entity Object
 * Created by vijay on 10/18/16.
 */
@Document(collection="retailstore")
public class StoreEntity {

    @Id
    @Indexed(unique=true)
    public BigInteger storeId;

    @Enumerated(EnumType.STRING)
    public StoreType storeType;

    @Indexed(unique=true)
    public String storeName;

    public AddressEntity address;

    public Optional<BigInteger> parentStoreId;

    public StoreEntity()
    {

    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param parentStoreId
     */
    public StoreEntity(BigInteger id, String storeType, String name, AddressEntity address, Optional<BigInteger> parentStoreId) {
        this.storeId = id;
        this.storeType = StoreType.fromString(storeType).orElse(StoreType.CHAIN);
        this.storeName = name;
        this.address = address;
        this.parentStoreId = parentStoreId;
    }

    @Override
    public String toString()
    {
        return this.storeType + " "  + this.storeName + " " + this.address + " " + this.parentStoreId;
    }
}
