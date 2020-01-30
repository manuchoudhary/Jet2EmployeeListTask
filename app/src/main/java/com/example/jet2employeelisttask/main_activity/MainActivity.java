package com.example.jet2employeelisttask.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jet2employeelisttask.R;
import com.example.jet2employeelisttask.model.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  MainContract.MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Employee> employeeList) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }

    @Override
    public void setDataToMainView(Employee employee) {

    }
}
