package com.books.shelf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.books.shelf.repo.FavRepository;
import com.books.shelf.room.FavTask;

import java.util.List;

public class BookDetailsViewModel extends AndroidViewModel {
    private FavRepository favRepository;
    private final MutableLiveData<List<FavTask>> Allfavs;
    public BookDetailsViewModel(@NonNull Application application) {
        super(application);
        favRepository = new FavRepository(application);
        Allfavs = favRepository.getAllFavs();
    }
    public MutableLiveData<List<FavTask>> getAllfavss() {return Allfavs;}
}
