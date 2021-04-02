package com.books.shelf;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.books.shelf.room.DatabaseClient;
import com.books.shelf.room.FavTask;
import com.books.shelf.viewmodel.BookDetailsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class BookDetailsActivity extends AppCompatActivity {
    BookDetailsViewModel bookDetailsViewModel;
    ImageView bookThums, favIcon, nonFavIcon;
    CardView londdescCard;
    TextView tvTitle, tvAuthors, sdvisible, tvDate, tvisbn, tvShortDesc, tvLargeDesc;
    String mtvTitle, mtvAuthors, mtvDate, mtvisbn, mtvShortDesc, mtvLargeDesc, mimageUrl;
    private final List<List<FavTask>> favffList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookview);
        bookThums = findViewById(R.id.bookThums);
        favIcon = findViewById(R.id.FavIcon);
        nonFavIcon = findViewById(R.id.NotFavIcon);
        tvTitle = findViewById(R.id.mBookTitle);
        tvAuthors = findViewById(R.id.mBookAuthor);
        tvDate = findViewById(R.id.mBookDate);
        tvisbn = findViewById(R.id.mBookisbn);
        tvShortDesc = findViewById(R.id.mBookShortDesc);
        tvLargeDesc = findViewById(R.id.mBookLongDesc);
        sdvisible = findViewById(R.id.sdvisible);
        londdescCard = findViewById(R.id.londdescCard);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Book Details");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        mtvTitle = intent.getExtras().getString("title") + "";
        mtvAuthors = "AUTHORS: \n" + intent.getExtras().getString("author") + "";
        mtvDate = "DATE: \n" + intent.getExtras().getString("published_date") + "";
        mtvisbn = intent.getExtras().getString("isbn");
        mtvShortDesc = intent.getExtras().getString("shortdesc") + "";
        mtvLargeDesc = intent.getExtras().getString("longdesc") + "";
        mimageUrl = intent.getExtras().getString("imageUrl") + "";

        updateDetails();

        checkVisibility();
        nonFavIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFav(mtvisbn);
                Toast.makeText(BookDetailsActivity.this, mtvTitle + " is Added To Favorite", Toast.LENGTH_SHORT).show();
                favIcon.setVisibility(View.VISIBLE);
                nonFavIcon.setVisibility(View.GONE);
            }
        });
        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFav(mtvisbn);
                Toast.makeText(BookDetailsActivity.this, mtvTitle + " is Removed From Favorite", Toast.LENGTH_SHORT).show();
                favIcon.setVisibility(View.GONE);
                nonFavIcon.setVisibility(View.VISIBLE);
            }
        });
//        if (favffList.isEmpty()){
//            favIcon.setVisibility(View.GONE);
//            nonFavIcon.setVisibility(View.VISIBLE);
//        }

    }

    private void checkVisibility() {
        bookDetailsViewModel =new ViewModelProvider(this).get(BookDetailsViewModel.class);
        bookDetailsViewModel.getAllfavss().observe(this, new Observer<List<FavTask>>() {
            @Override
            public void onChanged(List<FavTask> favTasks) {
                favffList.add(favTasks);
                addvisible(favffList);
                Log.d("TAG", "checkVisibilityss: " + favffList);
            }
        });



    }


    private void addvisible(List<List<FavTask>> favffList) {

        for (List<FavTask> ft : favffList) {
            for (FavTask fav : ft) {
                String str = fav.getIsbn();
                Log.d("TAG", "checkVisibility: " + str);
                if (str.equals(mtvisbn)) {
                    favIcon.setVisibility(View.VISIBLE);
                    nonFavIcon.setVisibility(View.GONE);
                } else {
                    favIcon.setVisibility(View.GONE);
                    nonFavIcon.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void deleteFav(String str) {
        if (str == null) {
            return;
        }
        @SuppressLint("StaticFieldLeak")
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .favTaskDao()
                        .removeFavs(str);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();
    }

    private void saveFav(String str) {

        if (str.isEmpty()) {
            return;
        }
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //creating a task
                FavTask task = new FavTask();
                task.setIsbn(str);
                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .favTaskDao()
                        .insertFavs(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        SaveTask st = new SaveTask();
        if (!str.isEmpty()) {
            st.execute();
        }
    }


    private void updateDetails() {
        if (mtvTitle != null && !mtvTitle.equals(""))
            tvTitle.setText(mtvTitle);
        if (mtvAuthors != null && !mtvAuthors.equals(""))
            tvAuthors.setText(mtvAuthors);
        if (mtvisbn != null && !mtvisbn.equals(""))
            tvisbn.setText(mtvisbn);
        if (mtvDate != null && !mtvDate.equals(""))
            tvDate.setText(mtvDate);
        if (mtvShortDesc != null && !mtvShortDesc.equals("null") && !mtvShortDesc.equals(""))
            tvShortDesc.setText(mtvShortDesc);
        else {
            tvShortDesc.setVisibility(View.GONE);
            sdvisible.setVisibility(View.GONE);
        }
        if (mtvLargeDesc != null && !mtvLargeDesc.equals("") && !mtvLargeDesc.equals("null"))
            tvLargeDesc.setText(mtvLargeDesc);
        else {
            londdescCard.setVisibility(View.GONE);
        }
        if (mimageUrl != null && !mimageUrl.equals("") && !mimageUrl.equals("null"))
            Picasso.get().load(mimageUrl).into(bookThums);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
