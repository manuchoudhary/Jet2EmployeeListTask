package com.example.jet2employeelisttask.employee_detail_activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.example.jet2employeelisttask.R;
import com.example.jet2employeelisttask.app.AppController;
import com.example.jet2employeelisttask.model.Employee;

public class DetailActivity extends AppCompatActivity implements DetailContract.DetailView {

    private ProgressBar progressBar;
    private TextView firstName, lastName, dateOfBirth, location, email, phone;
    private com.android.volley.toolbox.NetworkImageView displayPic;
    DetailContract.Presenter presenter;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initializeView();
        initProgressBar();

        presenter = new DetailPresenterImpl(this);
        presenter.requestData();
    }

    private void initializeView() {
        imageLoader = AppController.getInstance().getImageLoader();
        displayPic = findViewById(R.id.displayPic);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        location = findViewById(R.id.location);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
    }

    /**
     * Initializing progressbar programmatically
     * */
    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        final RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToView() {
        Employee employee = (Employee) getIntent().getSerializableExtra("Employee");
        setData(employee);
    }

    private void setData(Employee employee) {
        displayPic.setImageUrl(employee.getPicture().getLarge(), imageLoader);
        firstName.setText("First Name : " + employee.getName().getFirst());
        lastName.setText("Last Name : " + employee.getName().getLast());
        dateOfBirth.setText("DOB : " + employee.getDob().getDate());
        location.setText("Location : " + employee.getLocation().getState() + ", " + employee.getLocation().getCountry());
        email.setText("Email : " + employee.getEmail());
        phone.setText("Phone : " + employee.getPhone());
    }
}
