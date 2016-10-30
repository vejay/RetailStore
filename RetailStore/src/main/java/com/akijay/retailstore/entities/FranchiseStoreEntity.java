package com.akijay.retailstore.entities;

import com.akijay.retailstore.domain.store.Store;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by vijay on 10/23/16.
 */
public class FranchiseStoreEntity extends StoreEntity {

    public Dimension dimensions = new Dimension();

    public static class Dimension {
         public int length;
         public int width;
         public int height;
    }

    public FranchiseStoreEntity(){
    }

    /**
     *
     * @param id
     * @param name
     * @param address
     * @param parentStoreId
     */
    public FranchiseStoreEntity(BigInteger id, String storeType, String name, AddressEntity address,
                                 Optional<BigInteger> parentStoreId,int length, int width, int height) {

        super(id, storeType, name, address, parentStoreId);
        this.dimensions.length = length;
        this.dimensions.width = width;
        this.dimensions.height = height;
    }

}
