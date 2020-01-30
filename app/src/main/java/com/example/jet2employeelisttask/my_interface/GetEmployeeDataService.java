package com.example.jet2employeelisttask.my_interface;

import com.example.jet2employeelisttask.model.EmployeeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetEmployeeDataService {

    @GET("/")
    Call<EmployeeList> getEmployeeData(@Query("results") int count);
}
