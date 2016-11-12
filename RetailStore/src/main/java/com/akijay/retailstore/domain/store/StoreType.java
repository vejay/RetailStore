package com.akijay.retailstore.domain.store;

/**
 * Created by vijay on 10/22/16.
 */
public enum StoreType {

    CHAIN("Chain"), FRANCHISE("Franchise"), UNKNOWN("Unknown");

    private String value;

    StoreType(String value) {
        this.value = value;
    }

    public static StoreType from(String storeType) {
        return StoreType.valueOf(storeType) != null ? StoreType.valueOf(storeType) : StoreType.UNKNOWN;
    }

    public String value() {
        return this.value;
    }

}
