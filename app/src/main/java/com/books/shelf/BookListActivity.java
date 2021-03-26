package com.books.shelf;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.books.shelf.adapter.BookListAdapter;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import com.books.shelf.viewmodel.BookListViewModel;
import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {
    RecyclerView rv;
    private BookListAdapter adapter;
    private ArrayList<Book> BookList = new ArrayList<Book>();
    BookListViewModel bookListViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        rv = findViewById(R.id.booklistRecyclerView);
        callApi();
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
                    adapter.notifyDataSetChanged();
                } else {
                    Throwable e = apiResponse.getError();
                    Toast.makeText(getApplicationContext(), "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Error is " + e.getLocalizedMessage());
                }
            }
        });
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new BookListAdapter( BookList,this,"java");
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}