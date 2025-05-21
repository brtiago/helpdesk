package com.tiago.Helpdesk.service;

public abstract class BaseService {

    protected void validateId(Integer id, String entityName){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid " + entityName + " ID: " + id);
        }
    }
}
