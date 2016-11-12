package com.akijay.retailstore.domain.store;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Created by vijay on 10/22/16.
 */
public class StoreId {

    private BigInteger id;

    StoreId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof StoreId) {
            return this.id.equals(((StoreId)o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
