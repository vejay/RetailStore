package com.akijay.retailstore.controllers;

import com.akijay.retailstore.domain.store.Store;
import com.akijay.retailstore.domain.store.StoreService;
import com.akijay.retailstore.model.StoreModel;
import com.akijay.retailstore.model.error.WebServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class RetailStoreController {

    //@Autowired
    //private ApplicationService appService;

    @Autowired
    private StoreService storeService;

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public Store createStore(@Valid @RequestBody StoreModel store) {
        System.out.println("Create chain store called with store = " + store);
        return storeService.createStore(store);
    }

    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public List<StoreModel> getAllStores() {
        return storeService.getAllStores();
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET)
    public StoreModel getStoreDetails(Long id) {
        System.out.println("Get chain store called for storeId = " + id);
        return storeService.getStore(id);
    }

    @RequestMapping(value = "/store", method = RequestMethod.PUT)
    public String updatetore() {
        System.out.println("Update chain store called");
        return "Updating";
    }

    @RequestMapping(value = "/store", method = RequestMethod.DELETE)
    public void removeStore(@RequestParam Long storeId) {
        System.out.println("Delete chain store called for storeId=" + storeId);
        storeService.deleteStore(storeId);
    }

    @RequestMapping(value = "/store/chain/{id}", method = RequestMethod.GET)
    public List<StoreModel> getFranchiseStore(@PathVariable String id) {
        System.out.println("Get chain store called");
        if (id.equals("all")) {
            System.out.println("Fetching all chain stores");
            return storeService.getAllChainStores();
        }
        return null;
    }

    @RequestMapping(value = "/store/chain/all", method = RequestMethod.DELETE)
    public void deleteAllChainStores() {
        System.out.println("Delete all chain store called");
        storeService.deleteAllStores();
    }

    //@ResponseBody not needed since we have @RestController at the top of class.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<WebServiceError>> handleValidationException(MethodArgumentNotValidException ex) {
        List<WebServiceError> wsErrors = new ArrayList<>();
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        for (ObjectError error : errors) {
            WebServiceError wsError = WebServiceError.build(WebServiceError.WebServiceErrorType.VALIDATION_ERROR, error.getObjectName() + " " + error.getDefaultMessage());
            wsErrors.add(wsError);
        }

        ResponseEntity<List<WebServiceError>> responseEntity = ResponseEntity.badRequest().body(wsErrors);

        return responseEntity;
    }
}