package com.akijay.retailstore.domain.store;

import java.util.Objects;

/**
 * Created by vijay on 10/22/16.
 */
public class FranchiseStore extends Store {

    private FranchiseId franchiseId;

    private RetailType retailType;

    private StoreDimension dimensions;

    FranchiseStore(StoreId id, String name, StoreType type, StoreAddress address,
                   FranchiseId franchiseId, RetailType retailType, StoreDimension dimensions) {

        super(id, name, type, address);
        this.franchiseId = franchiseId;
        this.retailType = retailType;
        this.dimensions = dimensions;
    }

    public FranchiseId getFranchiseId() {
        return franchiseId;
    }

    public RetailType getRetailType() {
        return retailType;
    }

    public StoreDimension getDimensions() {
        return dimensions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FranchiseStore that = (FranchiseStore) o;
        return  Objects.equals(franchiseId, that.franchiseId) &&
                Objects.equals(retailType, that.retailType) &&
                Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(franchiseId, retailType, dimensions);
    }
}
