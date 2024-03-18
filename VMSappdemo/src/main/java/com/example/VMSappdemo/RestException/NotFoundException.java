package com.example.VMSappdemo.RestException;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){

    }

    public NotFoundException(String message){
        super(message);
    }


}
