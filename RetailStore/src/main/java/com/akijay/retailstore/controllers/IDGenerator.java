package com.akijay.retailstore.controllers;

import com.akijay.retailstore.model.StoreModel;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * Created by vijay on 10/23/16.
 */
@RestController
/**
 * Need to use NTP if we need to synchronize clocks across multiple machines.
 */
public class IDGenerator {

    //TODO: Change to use an Atomic BigInteger for thread safety.
    private static BigInteger uid = BigInteger.ZERO;

    public static BigInteger generateUniqueId() {
        uid =  uid.add(BigInteger.ONE);
        return uid;
    }

    private int getPrefix(String type) {
        switch(type) {
            case "CHAIN":
                return 900001;

            case "FRANCHISE":
                return 510001;
            default:
                throw new IllegalArgumentException(String.format("Invalid type. %s is not defined", type));
        }
    }

   @RequestMapping(value="/idgenerator/{type}", method= RequestMethod.GET)
    public BigInteger generateUniqueId(@PathVariable String type) {

       int prefix = getPrefix(type);
       long now = System.nanoTime();
       String finalId = String.valueOf(prefix).concat(String.valueOf(now));
       BigInteger uid = new BigInteger(finalId);
       return uid;
    }
}
