package com.example.jet2employeelisttask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EmployeeList implements Serializable {

    @SerializedName("results")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
