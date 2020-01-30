package com.example.jet2employeelisttask.employee_detail_activity;

public class DetailPresenterImpl implements DetailContract.Presenter {

    DetailContract.DetailView detailView;

    public DetailPresenterImpl(DetailContract.DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void onDestroy() {
        detailView = null;
    }

    @Override
    public void requestData() {
        detailView.setDataToView();
    }
}
