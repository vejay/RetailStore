package com.akijay.retailstore.model.error;

/**
 * Created by vijay on 10/27/16.
 */
public class WebServiceError {

    private WebServiceErrorType type;
    private String message;

    public static WebServiceError build(WebServiceErrorType type, String message) {
        WebServiceError error = new WebServiceError();
        error.type = type;
        error.message = message;

        return error;
    }

    public enum WebServiceErrorType {
        VALIDATION_ERROR
    }



}
