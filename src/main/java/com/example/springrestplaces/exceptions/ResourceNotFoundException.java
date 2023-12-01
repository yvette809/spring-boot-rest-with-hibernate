package com.example.springrestplaces.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private final Integer id;


    public ResourceNotFoundException(String message,Integer id) {
        super(message);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}