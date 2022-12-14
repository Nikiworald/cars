package com.haemimont.cars.core.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Storage<T> {
    T objT;


    Map<Integer,T> mapInStorage = new Map<Integer, T>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public T get(Object key) {
            return null;
        }

        @Override
        public T put(Integer key, T value) {
            return null;
        }

        @Override
        public T remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Integer, ? extends T> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Integer> keySet() {
            return null;
        }

        @Override
        public Collection<T> values() {
            return null;
        }

        @Override
        public Set<Entry<Integer, T>> entrySet() {
            return null;
        }
    };
    public Storage() {

    }

    public void carsToStorage(T[] ts){
        for(int i =0;i< ts.length;i++){
            mapInStorage.put(i,ts[i]);
        }

    }





}
