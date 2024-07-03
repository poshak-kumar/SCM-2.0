package com.scm.SCM_20.helpers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(){
        super("Resource Not Found");
    }

}
