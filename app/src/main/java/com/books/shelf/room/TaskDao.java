package com.books.shelf.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.books.shelf.common.MyTypeConverters;

import java.util.List;
@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAll();

    @Insert
    void insert(Task task);

    @Query("DELETE FROM Task")
    void delete();
//    @Query("DELETE FROM task WHERE id=:id")
//    void delete(int id);

//    @Query("UPDATE task SET First_Name=:First_Name,Last_Name=:Last_Name,Number=:Number,Email=:Email WHERE id=:id")
//    void update(String First_Name,String Last_Name,String Number,String Email,int id);
}
