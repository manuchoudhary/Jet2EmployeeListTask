package com.example.jet2employeelisttask.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.jet2employeelisttask.R;
import com.example.jet2employeelisttask.adapter.EmployeeAdapter;
import com.example.jet2employeelisttask.model.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private RecyclerView recyclerView;
    private MainContract.Presenter presenter;
    private RelativeLayout relRelativeLayout,reLoading;
    private Button btnReButton;
    private ProgressBar progressBar;
    private SearchView searchView;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();
        initializeView();
        initProgressBar();

        presenter = new MainPresenterImpl(this, new GetEmployeeInteractorImpl());
        callApi();
    }

    private void callApi(){
        reLoading.setVisibility(View.VISIBLE);
        presenter.requestDataFromServer();
    }

    /**
     * Initializing RecyclerView
     */
    private void initializeRecyclerView() {

        recyclerView = findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Initializing View
     */
    private void initializeView() {
        btnReButton = findViewById(R.id.btn_relaod);
        relRelativeLayout = findViewById(R.id.rel_reload);
        reLoading = findViewById(R.id.loading);
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

        btnReButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relRelativeLayout.setVisibility(View.GONE);
                callApi();
            }
        });
    }

    /**
     * RecyclerItem click event listener
     * */
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Employee employee) {
            Toast.makeText(getApplication(), employee.getName().getFirst(), Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        reLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Employee> employeeList) {
        adapter = new EmployeeAdapter(employeeList, recyclerItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        relRelativeLayout.setVisibility(View.VISIBLE);
        reLoading.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            presenter.onRefreshButtonClick();
        }

        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setDataToMainView(Employee employee) {

    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
