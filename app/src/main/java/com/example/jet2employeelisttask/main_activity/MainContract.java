package com.example.jet2employeelisttask.main_activity;

import com.example.jet2employeelisttask.model.Employee;

import java.util.List;

public interface MainContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     * */
    interface Presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer();

    }

    /**
     * showProgress() and hideProgress() would be used for displaying and hiding the progressBar
     * while the setDataToRecyclerView and onResponseFailure is fetched from the GetEmployeeInteractorImpl class
     **/
    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Employee> employeeList);

        void onResponseFailure(Throwable throwable);

        void setDataToMainView(Employee employee);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetEmployeeIntractor {

        interface OnFinishedListener {
            void onFinished(List<Employee> employeeList);
            void onFailure(Throwable t);
        }

        void getEmployeeList(OnFinishedListener onFinishedListener);
    }

}
