package com.example.notify.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private final LiveData<List<Task>> mAllTask;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTask = mRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return mAllTask;
    }

    public void insert(Task task){
        mRepository.insert(task);
    }

    public void delete(Task task){
        mRepository.delete(task);
    }

    public void update(Task task){
        mRepository.update(task);
    }
}
