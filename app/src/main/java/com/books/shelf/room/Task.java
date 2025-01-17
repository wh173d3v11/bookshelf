package com.books.shelf.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "my_category")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "category")
    private String category;

    @Ignore
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
