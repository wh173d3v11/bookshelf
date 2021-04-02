package com.books.shelf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.books.shelf.adapter.BookListAdapter;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import com.books.shelf.viewmodel.BookListViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BookListActivity extends AppCompatActivity {
    RecyclerView rv;
    private BookListAdapter adapter;
    private final ArrayList<Book> BookList = new ArrayList<Book>();
    private final ArrayList<Book> OurBookList = new ArrayList<Book>();
    BookListViewModel bookListViewModel;
    String CatName;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        rv = findViewById(R.id.booklistRecyclerView);
        progressBar = findViewById(R.id.progressbar);
        Intent intent = getIntent();
        CatName = intent.getExtras().getString("category");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(CatName);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        callApi();
        setupRecyclerView();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    private void callApi() {

        bookListViewModel = new ViewModelProvider(this).get(BookListViewModel.class);

        bookListViewModel.getData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse == null) {
                    return;
                }
                if (apiResponse.getError() == null) {
                    List<Book> bookCAts = apiResponse.getPosts();

                    BookList.addAll(bookCAts);
                    findCAtBook(BookList);
                    //Log.d("TAG", "findCAtBook:1 " + BookList);
                    //adapter.notifyDataSetChanged();
                } else {
                    Throwable e = apiResponse.getError();
                    Toast.makeText(getApplicationContext(), "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Error is " + e.getLocalizedMessage());
                }
            }
        });


    }

    private void findCAtBook(ArrayList<Book> bookList) {
        for (Book book : bookList) {
            for (String str : book.getCategories()) {
                // Log.d("TAG", "str: " + str);
                if (str.contains(CatName)) {
                    //System.out.println(book.getTitle());
                    //Log.d("TAG", "findCAtBook: " + book.getIsbn());
                    //saveTask(str);
                    if (book.getIsbn() != null) {
                        OurBookList.add(book);
                    }

                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new BookListAdapter(OurBookList, this,progressBar);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}