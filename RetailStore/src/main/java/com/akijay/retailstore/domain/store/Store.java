package com.akijay.retailstore.domain.store;

import com.akijay.retailstore.controllers.IDGenerator;
import com.akijay.retailstore.entities.StoreEntity;

import java.math.BigInteger;
import java.util.*;

/**
 * StoreEntity Domain Oject - Aggregate Root
 */
public class Store
{
    private StoreId id;

    private String name;

    private StoreType type;

    private StoreAddress address;

    private Collection<FranchiseStore> franchiseStores = new ArrayList<>();

    Store() {
    }

    public StoreId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StoreType getType() {
        return type;
    }

    public StoreAddress getAddress() {
        return address;
    }

    public Collection<FranchiseStore> getFranchiseStores() {
        return franchiseStores;
    }

    Store(StoreId id, String name, StoreType type, StoreAddress address) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
    }

    void addFranchsise(FranchiseStore franchise) {
        this.getFranchiseStores().add(franchise);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) &&
                Objects.equals(name, store.name) &&
                Objects.equals(type, store.type) &&
                Objects.equals(address, store.address) &&
                Objects.equals(franchiseStores, store.franchiseStores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, address, franchiseStores);
    }

    public static Store from(StoreEntity entity) {

        StoreType type = StoreType.from(entity.storeType.value());
        StoreAddress address = StoreAddress.from(entity.address);
        StoreId storeId = new StoreId(entity.storeId);
        Store store = new Store(storeId, entity.storeName,type, address);
        Store retrieved;
        if(store.getType().equals(StoreType.FRANCHISE)) {
            //Franchise store
            BigInteger id = IDGenerator.generateUniqueId();
            FranchiseStore franchise = new FranchiseStore(storeId, entity.storeName, type, address,
                    new FranchiseId(id),  RetailType.GROCERY, null);

            store.getFranchiseStores().add(franchise);
            retrieved  = franchise;
        } else {
            retrieved = store;
        }

        System.out.println("StoreId = " + retrieved.getId());
        return retrieved;
    }





}
