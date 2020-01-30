package com.example.jet2employeelisttask.main_activity;

import android.util.Log;

import com.example.jet2employeelisttask.model.EmployeeList;
import com.example.jet2employeelisttask.my_interface.GetEmployeeDataService;
import com.example.jet2employeelisttask.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetEmployeeInteractorImpl implements MainContract.GetEmployeeIntractor {

    @Override
    public void getEmployeeList(final OnFinishedListener onFinishedListener) {
        /** Create handle for the RetrofitInstance interface*/
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);

        /** Call the method with parameter in the interface to get the employee data*/
        Call<EmployeeList> call = service.getEmployeeData(50);

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");
        Log.d("URL Called========", call.request().url() + "");

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {
                onFinishedListener.onFinished(response.body().getEmployeeList());

            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
