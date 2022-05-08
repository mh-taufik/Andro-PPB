package com.example.notify.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application){
        TaskDatabase db = TaskDatabase.getInstance(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAll();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    void insert(Task task){
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }

    void delete(Task task){
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.delete(task);
        });
    }

    void update(Task task){
        TaskDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.update(task);
        });
    }
}
