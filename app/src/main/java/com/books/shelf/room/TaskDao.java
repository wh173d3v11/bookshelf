package com.books.shelf.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface TaskDao {

    @Query("SELECT * FROM my_category")
    LiveData<List<Task>> getAll();

    @Insert
    void insert(Task task);

    @Query("DELETE FROM my_category")
    void delete();

}
