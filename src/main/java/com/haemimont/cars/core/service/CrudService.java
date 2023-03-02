package com.haemimont.cars.core.service;

import java.util.ArrayList;

public abstract class CrudService<T> {

    public ArrayList<T> get(String critaria,String value) {
        return null;
    }


    public String put(T obj) {
        return null;

    }

    T update(T obj) {
        return null;
    }

    boolean delete(int id) {
        return false;
    }

}
