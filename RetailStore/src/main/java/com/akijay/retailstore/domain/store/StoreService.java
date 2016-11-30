package com.akijay.retailstore.domain.store;

import com.akijay.retailstore.entities.AddressEntity;
import com.akijay.retailstore.entities.StoreEntity;
import com.akijay.retailstore.model.StoreModel;
import com.akijay.retailstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Domain Service in DDD Layer.
 */
@Component
public class StoreService {


    @Autowired
    private StoreRepository repository;

    private BigInteger getParentStoreId(Optional<String> parentStoreName) {
        if(parentStoreName != null) {
            StoreEntity parentStore =  repository.findByStoreName(parentStoreName.get());
            System.out.println("parentStoreId = " + parentStore.storeId.toString());
            return parentStore.storeId;
        } else {
            throw new IllegalStateException("Parent store " + parentStoreName + " not found");
        }
    }

    public Store createStore(StoreModel newStore) {

        StoreModel.Address address = newStore.address;
        AddressEntity addressEntity = new AddressEntity(address.address1, address.address2, address.zipCode);
        System.out.println("newStore.parentStoreName = " + newStore.parentStoreName);

        Optional<BigInteger> parentId;
        if(newStore.parentStoreName != null) {
            BigInteger parentStoreId = getParentStoreId(newStore.parentStoreName);
            parentId = Optional.of(parentStoreId);
        } else {
            parentId = Optional.empty();
        }

        StoreEntity entity = new StoreEntity(newStore.id, newStore.type, newStore.name, addressEntity, parentId);
        StoreEntity saved = repository.save(entity);

        return Store.from(saved);
    }

    public Store createStore(BigInteger storeId, String storeType, String name,
                             String address1, String address2, int zipCode, Optional<BigInteger> parentStoreId) {

        AddressEntity address = new AddressEntity(address1, address2, zipCode);
        StoreEntity newStore = null;
        StoreEntity entity = new StoreEntity(storeId,storeType, name, address, parentStoreId);
        if(parentStoreId.isPresent()) {
           //Do nothing.
        } else {
            newStore = entity;
        }

        StoreEntity created = repository.save(newStore);
        return Store.from(created);
    }

    public List<StoreModel> getAllStores() {
        List<StoreModel> models = new ArrayList<>();
        Iterable<StoreEntity> allStores = repository.findAll();
        for(StoreEntity entity: allStores) {
            System.out.println("From Store = " + entity.storeType);
            Store store = Store.from(entity);
            models.add(StoreModel.from(store));
        }
        return models;
    }

    public List<StoreModel> getAllChainStores() {
        Stream<StoreModel> allStores = getAllStores().stream();
        return allStores.filter(s -> s.type.equals("Chain"))
                         .collect(Collectors.toList());
    }

    public void deleteAllStores() {
        repository.deleteAll();
    }

    public void deleteStore(Long storeId) {
        try {
            repository.delete(storeId);
        } catch(IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    public StoreModel getStore(Long storeId) {
        StoreEntity entity = repository.findOne(storeId);
        return StoreModel.from(Store.from(entity));
    }

    public Store updateStore() {
        throw new UnsupportedOperationException();
    }

    public boolean deleteStore() {
        throw new UnsupportedOperationException();
    }

}
