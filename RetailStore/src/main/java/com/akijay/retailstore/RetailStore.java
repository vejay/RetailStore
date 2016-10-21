package com.akijay.retailstore;

import com.akijay.retailstore.entities.Store;
import com.akijay.retailstore.services.storeServices.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class RetailStore
{
    @Autowired
    private StoreService storeService;

    //@RequestMapping("/")
    String home()
    {
        return "Hello World From RetailStore1";
    }

    @RequestMapping(
            value = "/chainStore",
            method = RequestMethod.POST
        )

    /*public Store createChainStore(
            @RequestParam BigInteger  storeId,
            @RequestParam String name,
            @RequestParam String address1,
            @RequestParam String address2,
            @RequestParam Integer zipCode,
            @RequestParam Optional<Long> parentStoreId

    ){
        System.out.println("Create chain store called with params = " + storeId  + " " + name + " "  + address1 + " " + address2 + " " + zipCode + " " + parentStoreId);
        return storeService.createStore(storeId, name, address1, address2, zipCode, parentStoreId);
    }*/


    public Store createChainStore(@RequestBody Store store){
        System.out.println("Create chain store called with store = " + store);
        return storeService.createStore(store);
    }

    @RequestMapping(value="/chainStore", method=RequestMethod.GET)
    public Store getChainStoreDetails(Long storeId) {
        System.out.println("Get chain store called for id = " + storeId);
        return storeService.getStore(storeId);
    }

    @RequestMapping(value="/chainStore", method=RequestMethod.DELETE)
    public void removeChainStore(
            @RequestParam Long storeId
    ) {
        System.out.println("Delete chain store called for id=" + storeId);
        storeService.deleteStore(storeId);
    }

    @RequestMapping(value="/chainStore", method=RequestMethod.PUT)
    public String updateChainStore() {
        System.out.println("Update chain store called");
        return "Updating";
    }

    @RequestMapping(value="/chainStore/all", method=RequestMethod.GET)
    public List<Store> getAllChainStores(){
        System.out.println("Get all chain stores called");
        return storeService.getAllStores();
    }

    @RequestMapping(value="chainStore/all", method=RequestMethod.DELETE)
    public void deleteAllChainStores(){
        System.out.println("Delete all chain store called");
        storeService.deleteAllStores();
    }

    @RequestMapping(value = "/franchise", method= RequestMethod.POST)
    public String createStore() {
        System.out.println("CreateFranchise called");
        return "Creating Franchise";
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(RetailStore.class, args);

    }
}