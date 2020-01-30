package com.example.jet2employeelisttask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DOB implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("age")
    private String age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
