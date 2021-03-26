package com.books.shelf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.R;
import com.books.shelf.network.Book;
import com.books.shelf.room.Task;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookslistViewHolder> {

    ArrayList<Book> bookList;
    Context context;
    String catval;
    public BookListAdapter(ArrayList<Book> bookList, Context context,String catval) {
    this.bookList = bookList;
    this.context = context;
    this.catval = catval;
    }

    @NonNull
    @Override
    public BookslistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list, parent, false);
        return new BookListAdapter.BookslistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookslistViewHolder holder, int position) {
        Book bk=bookList.get(position);
        for (String str : bk.getCategories()){
            if (str.equals(catval)){
                System.out.println(bk.getAuthors().toString() + " " +bk.getPageCount() + "");
            }
        }
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class BookslistViewHolder extends RecyclerView.ViewHolder{

        TextView tvtitle,tvAuth,tvDate,tvCount;
        ImageView thumbnail;

        public BookslistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.bookTitle);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            tvAuth = itemView.findViewById(R.id.auths);
            tvDate=itemView.findViewById(R.id.date);
            tvCount  =    itemView.findViewById(R.id.pgCount);
        }
    }
}
