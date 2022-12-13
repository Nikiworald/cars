package com.haemimont.cars.core.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Storage {
    Map<Object,Object> map = new Map<Object, Object>(){
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
        public Object get(Object key) {
            return null;
        }

        @Override
        public Object put(Object key, Object value) {
            return null;
        }

        @Override
        public Object remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<?, ?> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Object> keySet() {
            return null;
        }

        @Override
        public Collection<Object> values() {
            return null;
        }

        @Override
        public Set<Entry<Object, Object>> entrySet() {
            return null;
        }
    };



}
