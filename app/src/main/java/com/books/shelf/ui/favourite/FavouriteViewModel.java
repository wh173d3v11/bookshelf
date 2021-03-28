package com.books.shelf.ui.favourite;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.books.shelf.network.ApiResponse;
import com.books.shelf.repo.BookRepository;
import com.books.shelf.repo.FavRepository;
import com.books.shelf.room.FavTask;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {
    private MediatorLiveData<ApiResponse> mApiResponse;
    private final BookRepository mApiRepo;
    private FavRepository favRepository;
    private final MutableLiveData<List<FavTask>> Allfavs;
    public FavouriteViewModel(@NonNull Application application) {
        super(application);
        favRepository = new FavRepository(application);
        Allfavs = favRepository.getAllFavs();
        mApiResponse = new MediatorLiveData<>();
        mApiRepo = new BookRepository();
    }
    public MutableLiveData<ApiResponse> getData() {
        mApiResponse.addSource(mApiRepo.getBooks(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse)            {
                mApiResponse.setValue(apiResponse);
            }
        });
        return mApiResponse;
    }
    public MutableLiveData<List<FavTask>> getAllfavss() {return Allfavs;}
}