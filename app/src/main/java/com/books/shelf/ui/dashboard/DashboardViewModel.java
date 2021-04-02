package com.books.shelf.ui.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.books.shelf.repo.DataRepository;
import com.books.shelf.room.Task;

import java.util.List;



public class DashboardViewModel extends AndroidViewModel {
    private DataRepository mRepository;

    private final LiveData<List<Task>> mAllWords;

    public DashboardViewModel (Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mAllWords = mRepository.getAllWords();
    }
    LiveData<List<Task>> getAllCats() { return mAllWords; }
}