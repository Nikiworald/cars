package com.haemimont.cars.core.model;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Storage<T> {
    T obj;
    T[] objs;
    public Storage(T[] objs){
        this.objs = objs;
        inToMap(objs);
    }
    HashMap map = new HashMap();
    void inToMap(T[] objs) {
        for (int i = 1; i < objs.length;i++)
        {
            map.put(i,objs[i]);
            T cart = objs[i];
        }

    }


}
