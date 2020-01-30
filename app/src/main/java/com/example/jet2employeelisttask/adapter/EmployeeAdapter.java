package com.example.jet2employeelisttask.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.example.jet2employeelisttask.R;
import com.example.jet2employeelisttask.app.AppController;
import com.example.jet2employeelisttask.main_activity.RecyclerItemClickListener;
import com.example.jet2employeelisttask.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> implements Filterable {

    private List<Employee> dataList;
    private List<Employee> filteredEmployeeList;
    private RecyclerItemClickListener recyclerItemClickListener;
    ImageLoader imageLoader;

    public EmployeeAdapter(List<Employee> dataList , RecyclerItemClickListener recyclerItemClickListener) {
        this.dataList = dataList;
        filteredEmployeeList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        imageLoader = AppController.getInstance().getImageLoader();
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.employee_card, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final EmployeeViewHolder viewHolder = holder;
        if (!TextUtils.isEmpty(filteredEmployeeList.get(position).getName().getFirst())) {
            viewHolder.first.setText("First Name : " + filteredEmployeeList.get(position).getName().getFirst());
            viewHolder.last.setText("Last Name : " + filteredEmployeeList.get(position).getName().getLast());
            viewHolder.gender.setText("Gender : " + filteredEmployeeList.get(position).getGender().toUpperCase());
            viewHolder.profilePic.setImageUrl(filteredEmployeeList.get(position).getPicture().getThumbnail(), imageLoader);
            viewHolder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerItemClickListener.onItemClick(filteredEmployeeList.get(position));
                }
            });
        } else {
            viewHolder.first.setVisibility(View.GONE);
            viewHolder.last.setVisibility(View.GONE);
            viewHolder.gender.setVisibility(View.GONE);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredEmployeeList = dataList;
                } else {
                    List<Employee> filteredList = new ArrayList<>();
                    for (Employee row : dataList) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getName().getFirst().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredEmployeeList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredEmployeeList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredEmployeeList = (ArrayList<Employee>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filteredEmployeeList.size();
    }


    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView first, last, gender;
        com.android.volley.toolbox.NetworkImageView profilePic;
        CardView card;

        EmployeeViewHolder(View itemView) {
            super(itemView);
            this.card = itemView.findViewById(R.id.card_view);
            this.first = itemView.findViewById(R.id.first);
            this.last = itemView.findViewById(R.id.last);
            this.gender = itemView.findViewById(R.id.gender);
            this.profilePic = itemView.findViewById(R.id.employeeThumbnailPic);
        }
    }
}
