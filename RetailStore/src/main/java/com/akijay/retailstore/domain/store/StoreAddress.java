package com.akijay.retailstore.domain.store;

import com.akijay.retailstore.entities.AddressEntity;

import java.util.Objects;

/**
 * Created by vijay on 10/22/16.
 */
public class StoreAddress {

    private String address1;

    private String address2;

    private Integer zipCode;

    private static int maxZipLength = 7;

    StoreAddress(){

    }

    public StoreAddress(String address1, String address2, Integer zipCode) {
        if(String.valueOf(zipCode).length() > maxZipLength) {
            throw new IllegalArgumentException("ZipCode length is greater than maximum accepted value of " + maxZipLength);
        }

        this.address1 = address1;
        this.address2 = address2;
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof StoreAddress) {
            StoreAddress other = (StoreAddress)o;
            return this.address1.equals(other.address1) &&
                    this.address2.equals(other.address2) &&
                    this.zipCode.equals(other.zipCode);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(address1, address2, zipCode);
    }

    public static StoreAddress from(AddressEntity entity) {
        StoreAddress address = new StoreAddress(entity.address1, entity.address2, entity.zipCode);
        return address;
    }
}
