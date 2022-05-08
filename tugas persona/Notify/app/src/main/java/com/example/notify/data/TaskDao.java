package com.example.notify.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

//    @Query("SELECT * FROM task_table WHERE name LIKE :name")
//    void findByName(String name);
//
//    @Query("SELECT * FROM task_table WHERE date LIKE :date")
//    void findByDate(String date);

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    LiveData<List<Task>> getAll();


}
