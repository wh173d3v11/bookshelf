package com.books.shelf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.R;
import com.books.shelf.network.Book;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.BooksViewHolder> {
    ArrayList<Book> bookList;
    Context cxt;
    public CatAdapter(Context context, ArrayList<Book> bookList) {
        cxt = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public CatAdapter.BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cxt).inflate(R.layout.book_cat, parent, false);
        return new  BooksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.BooksViewHolder holder, int position) {
        holder.tvCat.setText(bookList.get(position).getCategories().toString());
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
