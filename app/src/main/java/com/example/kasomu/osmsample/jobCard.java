package com.example.kasomu.osmsample;

/**
 * Created by kasomu on 8/5/2015.
 */
public class jobCard {
    private int id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String phone;

    public jobCard(int id, String name, String email, String address, String phone, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }
}
