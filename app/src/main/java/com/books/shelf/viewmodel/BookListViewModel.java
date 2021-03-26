package com.books.shelf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.books.shelf.network.ApiResponse;
import com.books.shelf.repo.BookRepository;

public class BookListViewModel extends ViewModel {
    private MediatorLiveData<ApiResponse> mApiResponse;
    private final BookRepository mApiRepo;

    public BookListViewModel() {
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
}
