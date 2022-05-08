package com.example.notify;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.notify.data.Task;
import com.example.notify.data.TaskListAdapter;
import com.example.notify.data.TaskViewModel;
import com.example.notify.notification.NotificationJobService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class ListFragment extends Fragment {
    private TaskViewModel taskViewModel;
    private List<Task> data;
    private Button add_btn, delete_btn;
    private JobScheduler mScheduler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final TaskListAdapter adapter = new TaskListAdapter(new TaskListAdapter.TaskDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), data -> {
            adapter.submitList(data);
            createNotification(data);
        });

        mScheduler = (JobScheduler) getContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        delete_btn = view.findViewById(R.id.finish_btn);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_listFragment_to_addFragment);
            }
        });

        //TODO: get bundle of request from another fragment
        if (getArguments() != null) {
            if (getArguments().getInt("request") == 1) {
                Task task = new Task(
                        getArguments().getString("name"),
                        getArguments().getString("description"),
                        getArguments().getString("date"));
                taskViewModel.insert(task);
            } else if (getArguments().getInt("request") == 2) {
                taskViewModel.delete(data.get(getArguments().getInt("id")));
            }
        }

        return view;
    }

    public void createNotification(List<Task> data){
        //TODO: add notification service to all data
        for(Task task : data) {
            LocalDate taskDate = LocalDate.parse(task.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate nowDate = LocalDate.now();
            if (!data.isEmpty()) {
                int JOB_ID = 0;
                ComponentName serviceName = new ComponentName(getContext(), NotificationJobService.class.getName());
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
                JobInfo myJobInfo = builder.build();
                mScheduler.schedule(myJobInfo);
                break;
            }
        }
    }
}
