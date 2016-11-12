package com.akijay.retailstore.entities;

import com.akijay.retailstore.domain.store.StoreAddress;

/**
 * Created by vijay on 10/18/16.
 */
public class AddressEntity {

    public String address1;

    public String address2;

    public int zipCode;

    public AddressEntity() {

    }

    public AddressEntity(String address1, String address2, int zipCode) {
        this.address1 = address1;
        this.address2 = address2;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address1 : " + address1 + " " + "Address2: " + address2 + "ZipCode: " + zipCode;
    }

    public static AddressEntity from(StoreAddress address) {
        AddressEntity entity  = new AddressEntity(address.getAddress1(), address.getAddress2(), address.getZipCode());
        return entity;
    }
}
