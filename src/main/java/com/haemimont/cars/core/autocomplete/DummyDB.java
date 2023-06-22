    package com.haemimont.cars.core.autocomplete;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
@Deprecated//currently not implemented
public class DummyDB {
    public DummyDB() {
        List<String> countries = new ArrayList<>();
        String data = "Afghanistan,	Albania, Zimbabwe";
        StringTokenizer st = new StringTokenizer(data, ",");

        while(st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }
//        int totalCountries = countries.size();
    }

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public List<String> getData(String query) {
//        String country;
////        query = query.toLowerCase();
//
//        List<String> matched = new ArrayList<>();
//        for(int i=0; i<totalCountries; i++) {
//            country = countries.get(i).toLowerCase();
//            if(country.startsWith(query)) {
//                matched.add(countries.get(i));
//            }
//        }
//        matched.add("hello");
//        return matched;
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
}