package com.example.notify.data;

import android.app.job.JobInfo;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notify.R;
import com.example.notify.notification.NotificationJobService;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    private TextView taskName, taskDate;
    private Button delete_btn;
    private ConstraintLayout layoutItems;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.text_name);
        taskDate = itemView.findViewById(R.id.text_date);
        delete_btn = itemView.findViewById(R.id.finish_btn);
        layoutItems = itemView.findViewById(R.id.items);
    }

    public void bind(String name, String date, String description, int id){
        taskName.setText(name);
        taskDate.setText(date);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("request",2);
                bundle.putInt("id",id);
                Navigation.findNavController(view).navigate(R.id.action_listFragment_to_itself, bundle);
            }
        });
        layoutItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("date",date);
                bundle.putString("description",description);
                Navigation.findNavController(view).navigate(R.id.action_listFragment_to_detailsFragment, bundle);
            }
        });
    }

    static TaskViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task, parent, false);
        return new TaskViewHolder(view);
    }
}
