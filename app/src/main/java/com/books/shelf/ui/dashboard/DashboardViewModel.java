package com.books.shelf.ui.dashboard;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.books.shelf.BookRepository;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;

import java.util.List;

public class DashboardViewModel extends ViewModel {
    private MediatorLiveData<ApiResponse> mApiResponse;
    private final BookRepository mApiRepo;

    public DashboardViewModel() {
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