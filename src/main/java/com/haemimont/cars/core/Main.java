package com.haemimont.cars.core;
import com.haemimont.cars.core.model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        String path="src/main/java/csv/cars.csv";
        arrayList = addToLinkList.addToArrayList(arrayList,path,5);
        System.out.println(arrayList);
        }
    }
