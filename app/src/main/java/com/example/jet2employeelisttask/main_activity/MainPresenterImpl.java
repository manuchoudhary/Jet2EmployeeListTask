package com.example.jet2employeelisttask.main_activity;

import com.example.jet2employeelisttask.model.Employee;

import java.util.List;

public class MainPresenterImpl implements MainContract.Presenter, MainContract.GetEmployeeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetEmployeeIntractor getEmployeeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetEmployeeIntractor getEmployeeIntractor) {
        this.mainView = mainView;
        this.getEmployeeIntractor = getEmployeeIntractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(mainView != null){
            mainView.showProgress();
        }
        getEmployeeIntractor.getEmployeeList(this);
    }

    @Override
    public void requestDataFromServer() {
        getEmployeeIntractor.getEmployeeList(this);
    }

    @Override
    public void onFinished(List<Employee> employeeList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(employeeList);
            mainView.setDataToMainView(employeeList.get(0));
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}
