package com.akijay.retailstore.model;

import com.akijay.retailstore.domain.store.FranchiseStore;
import com.akijay.retailstore.domain.store.Store;
import com.akijay.retailstore.domain.store.StoreId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by vijay on 10/23/16.
 */
public class StoreModel {

    public StoreModel() {

    }

    public static class Address {
        public String address1;
        public String address2;
        public int zipCode;
    }

    @NotNull
    @Min(value=999999999)
    public BigInteger id;

    @NotNull
    public String type;

    @NotNull
    @Length(min=3)
    public String name;

    @NotNull
    public Address address;

    public Optional<String> parentStoreName;

    public static StoreModel from(Store store) {

        System.out.println("StoreModel storeType  = " + store.getType());
        System.out.println("StoreModel storeType  value = " + store.getType().value());
        StoreModel model = new StoreModel();

        model.id = store.getId().getId();
        model.name = store.getName();
        model.address = new Address();
        model.type = store.getType().value();
        model.address.address1 = store.getAddress().getAddress1();
        model.address.address2 = store.getAddress().getAddress2();
        model.address.zipCode  = store.getAddress().getZipCode();


        if(store.getType().equals("Franchise")) {
            System.out.println("Franchise store encountered. Fetching franchise related information");
        }

        return model;
    }
}
