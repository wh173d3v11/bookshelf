package com.books.shelf.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.BookDetailsActivity;
import com.books.shelf.R;
import com.books.shelf.network.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.FavlistViewHolder> {
    Context context;
    ArrayList<Book> bookList;
    ProgressBar progressbar;
    public FavListAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;

    }

    @NonNull
    @Override
    public FavlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_list, parent, false);
        return new FavlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavlistViewHolder holder, int position) {

        Book bk=bookList.get(position);
        holder.tvtitle.setText(bk.getTitle());
        holder.tvAuth.setText(bk.getAuthors().toString().replace("[","").replace("]",""));
        String count = "Page Count : " + bk.getPageCount();
        holder.tvCount.setText(count);
        Picasso.get().load(bk.getThumbnailUrl()).fit().into(holder.thumbnail);
        holder.tvDate.setText(bk.getPublishedDate().get$date().substring(0,10));
        holder.booklistCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                intent = new Intent(context, BookDetailsActivity.class);
                if (bk.getTitle()!=null)
                    intent.putExtra("title",bk.getTitle().toString() +"");
                if (bk.getAuthors()!=null)
                    intent.putExtra("author",bk.getAuthors().toString().replace("[","").replace("]","") +"");
                if (bk.getIsbn()!=null)
                    intent.putExtra("isbn",bk.getIsbn().toString() +"");
                if (bk.getPublishedDate()!=null)
                    intent.putExtra("published_date",bk.getPublishedDate().get$date().substring(0,10) +"");
                if (bk.getShortDescription()!=null)
                    intent.putExtra("shortdesc",bk.getShortDescription().toString() +"");
                if (bk.getLongDescription()!=null)
                    intent.putExtra("longdesc",bk.getLongDescription().toString() +"");
                if (bk.getThumbnailUrl()!=null)
                    intent.putExtra("imageUrl",bk.getThumbnailUrl().toString() +"");
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class FavlistViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitle,tvAuth,tvDate,tvCount;
        ImageView thumbnail;
        CardView booklistCard;
        public FavlistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.bookTitle);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            tvAuth = itemView.findViewById(R.id.auths);
            tvDate=itemView.findViewById(R.id.date);
            tvCount  =    itemView.findViewById(R.id.pgCount);
            booklistCard = itemView.findViewById(R.id.booklistCard);
        }
    }
}
