package com.books.shelf.room;

import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface TaskDao {
    @Query("SELECT * FROM task ")
    List<Task> getAll();

    @Insert
    void insert(Task task);

//    @Query("DELETE FROM task WHERE id=:id")
//    void delete(int id);

//    @Query("UPDATE task SET First_Name=:First_Name,Last_Name=:Last_Name,Number=:Number,Email=:Email WHERE id=:id")
//    void update(String First_Name,String Last_Name,String Number,String Email,int id);
}
