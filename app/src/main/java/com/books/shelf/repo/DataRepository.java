package com.books.shelf.repo;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.books.shelf.room.AppDatabase;
import com.books.shelf.room.DatabaseClient;
import com.books.shelf.room.Task;
import com.books.shelf.room.TaskDao;

import java.util.List;


public class DataRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllCats;

    public DataRepository(Application application) {
       // AppDatabase db = AppDatabase.getDatabase(application);

        DatabaseClient db = DatabaseClient.getInstance(application);
        mTaskDao = db.getAppDatabase().taskDao();
        mAllCats = mTaskDao.getAll();
    }

    public LiveData<List<Task>> getAllWords() {
        return mAllCats;
    }

}
