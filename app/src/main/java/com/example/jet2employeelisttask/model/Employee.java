package com.example.jet2employeelisttask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Employee implements Serializable {

    @SerializedName("name")
    private Name name;

    @SerializedName("gender")
    private String gender;

    @SerializedName("picture")
    private Picture picture;

    @SerializedName("dob")
    private DOB dob;

    @SerializedName("location")
    private Location location;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public DOB getDob() {
        return dob;
    }

    public void setDob(DOB dob) {
        this.dob = dob;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
