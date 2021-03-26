package com.books.shelf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.books.shelf.network.ApiInterface;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import com.books.shelf.room.DatabaseClient;
import com.books.shelf.room.Task;
import com.books.shelf.viewmodel.SplashViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SplashScreen extends AppCompatActivity {
    ApiInterface apiService;
    SplashViewModel mainViewModel;
    List<String> CatListDB = new ArrayList<>();
    private ArrayList<Book> BookList = new ArrayList<Book>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        callApi();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private void callApi() {

        mainViewModel = new ViewModelProvider(this).get(SplashViewModel.class);

        mainViewModel.getData().observe(this, new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse == null) {
                    return;
                }
                if (apiResponse.getError() == null) {
                    List<Book> bookCAts = apiResponse.getPosts();
                    BookList.addAll(bookCAts);
                    addcat(BookList);
                } else {
                    Throwable e = apiResponse.getError();
                    Toast.makeText(getApplicationContext(), "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Error is " + e.getLocalizedMessage());
                }
            }
        });
    }


    private void addcat(ArrayList<Book> bookiList) {


        int count = 1;
        HashMap<String, Integer> hmap = new HashMap<>();
        for (Book book : bookiList) {
            if (!hmap.containsKey(book.getCategories().toString().replace("[", ""))) {
                for (String str : book.getCategories()) {
                    if (!hmap.containsKey(str) && !hmap.containsKey(book.getCategories().toString().replace("[", ""))) {
                        hmap.put(str, count);
                        System.out.println(str);
                        Log.d("TAG", "addcat: " + str);
                        // Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                        saveTask(str);
                        //CatListDB.add(str);
                    }
                }
            }
        }
    }

    private void saveTask(String str) {
        if (str.isEmpty()) {
            return;
        }
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setCategory(str);


                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //  Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        if (!str.isEmpty()) {
            st.execute();
        }
    }

}
