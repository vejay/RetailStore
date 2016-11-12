package com.akijay.retailstore.domain.store;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Created by vijay on 10/22/16.
 */
public class FranchiseId {

    private BigInteger id;

    FranchiseId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if(o instanceof FranchiseId) {
            return this.id.equals(((FranchiseId)o).getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
