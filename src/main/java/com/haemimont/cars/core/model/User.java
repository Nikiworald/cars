package com.haemimont.cars.core.model;

public class User {
    private final String NAME;
    private final String PASSWORD;
    private final String EMAIL;
    private String phoneNumber;

    public User(String name, String password, String email, String phoneNumber) {
        this.NAME = name;
        this.PASSWORD = password;
        this.EMAIL = email;
        this.phoneNumber = phoneNumber;
    }

    public String getNAME() {
        return NAME;
    }

    // --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public void setName(String name) {
//        this.name = name;
//    }
// --Commented out by Inspection START (6/22/2023 2:58 PM):
//// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
//
    public String getPassword() {
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)
        return PASSWORD;
    }

    //    public void setPASSWORD(String PASSWORD) {
// --Commented out by Inspection START (6/22/2023 2:58 PM):
//        this.password = password;
//    }
//
    public String getEmail() {
        return EMAIL;
    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

// --Commented out by Inspection START (6/22/2023 2:58 PM):
//    public void setEmail(String email) {
//        this.email = email;
//    }
// --Commented out by Inspection STOP (6/22/2023 2:58 PM)

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Deprecated
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
