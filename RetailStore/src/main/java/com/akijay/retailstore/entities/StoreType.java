package com.akijay.retailstore.entities;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.empty;

/**
 * Created by vijay on 10/21/16.
 */
public enum StoreType {

    CHAIN("CHAIN"), FRANCHISE("FRANCHISE");

     private String value;

    StoreType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static Optional<StoreType> fromString(String value) {
        Optional<StoreType> type = Optional.empty();
        if(value != null)
        {
            try {
                type = Optional.of(StoreType.valueOf(value));
            } catch(Exception ex) {
                ex.printStackTrace();;
            }
        }
        return type;
    }

    public List<String> getAllTypes() {
        return Stream.of(StoreType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
