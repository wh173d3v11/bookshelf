package com.books.shelf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.R;
import com.books.shelf.network.Book;
import com.books.shelf.room.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.BooksViewHolder> {
    List<Task> bookList;
    Context cxt;

    public CatAdapter(List<Task> bookList, Context cxt) {
        this.bookList = bookList;
        this.cxt = cxt;
    }

    @NonNull
    @Override
    public CatAdapter.BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.book_cat, parent, false);
        return new  BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.BooksViewHolder holder, int position){
        Task bk=bookList.get(position);
        holder.tvCat.setText(bk.getCategory().toString());
        Log.d("TAG", "onBindViewHolder: "+ bk.getCategory().toString());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{

        TextView tvCat;


        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCat = itemView.findViewById(R.id.tvCAt);
        }
    }
}
