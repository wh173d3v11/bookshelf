package com.books.shelf.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.books.shelf.network.ApiClient;
import com.books.shelf.network.ApiInterface;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {

    private static BookRepository bookRepository;

    public static BookRepository getInstance(){
        if (bookRepository == null){
            bookRepository = new BookRepository();
        }
        return bookRepository;
    }

    private ApiInterface apiService;

    public BookRepository(){
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ApiResponse> getBooks() {
       final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
        Call<List<Book>> call = apiService.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()) {
                    apiResponse.postValue(new ApiResponse(response.body()));
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                apiResponse.postValue(new ApiResponse(t));
                Log.d("TAG", "Response = " + t.toString());
            }
        });
        return apiResponse;
    }
}
