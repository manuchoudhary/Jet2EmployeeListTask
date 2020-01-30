package com.example.jet2employeelisttask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EmployeeList implements Serializable {

    @SerializedName("results")
    private List<Emplyee> employeeList;

    public List<Emplyee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Emplyee> employeeList) {
        this.employeeList = employeeList;
    }
}
