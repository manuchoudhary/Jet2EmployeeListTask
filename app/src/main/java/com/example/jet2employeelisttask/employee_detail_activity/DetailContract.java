package com.example.jet2employeelisttask.employee_detail_activity;

import com.example.jet2employeelisttask.model.Employee;

public interface DetailContract {

    interface Presenter {

        void onDestroy();

        void requestData();
    }

    interface DetailView {
        void showProgress();

        void hideProgress();

        void setDataToView();
    }
}
