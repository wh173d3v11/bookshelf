package com.books.shelf.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.books.shelf.common.MyTypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

   // @TypeConverters(MyTypeConverters.class)
    @ColumnInfo(name = "category")
    private String category;


    public Task(){}
    public Task(int id,String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
