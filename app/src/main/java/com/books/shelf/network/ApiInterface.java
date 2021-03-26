package com.books.shelf.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("books/")
    Call<List<Book>> getBooks();
}
