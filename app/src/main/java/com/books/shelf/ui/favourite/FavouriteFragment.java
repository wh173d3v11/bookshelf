package com.books.shelf.ui.favourite;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.R;
import com.books.shelf.adapter.FavListAdapter;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import com.books.shelf.room.FavTask;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private FavouriteViewModel favViewModel;
    RecyclerView rv;
    FavListAdapter favListAdapter;
    private final List<List<FavTask>> favffList = new ArrayList<>();
    private final ArrayList<Book> BookList = new ArrayList<Book>();
    private final ArrayList<Book> OurBookList = new ArrayList<Book>();
    ProgressBar progressbar;
    TextView tv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favViewModel =
                new ViewModelProvider(this).get(FavouriteViewModel.class);

        View root = inflater.inflate(R.layout.fragment_favourite, container, false);
        rv = root.findViewById(R.id.rvFavs);
        tv = root.findViewById(R.id.fav_text);
        progressbar = root.findViewById(R.id.progressbarFav);
        getFavs();
        setupRvBooks();
        return root;
    }

    private void getFavs() {
        favViewModel.getAllfavss().observe(getViewLifecycleOwner(), new Observer<List<FavTask>>() {
            @Override
            public void onChanged(List<FavTask> favTasks) {
                favffList.add(favTasks);
                updateFavlist();
            }
        });
    }

    private void updateFavlist() {
        favViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if (apiResponse == null) {
                    return;
                }
                if (apiResponse.getError() == null) {
                    List<Book> bookCAts = apiResponse.getPosts();
                    BookList.addAll(bookCAts);
                    findFavsBook(BookList);
                } else {
                    Throwable e = apiResponse.getError();
                    Log.e("TAG", "Error is " + e);
                }
            }
        });
    }

    private void findFavsBook(ArrayList<Book> bookList) {

        for (List<FavTask> ff : favffList) {
            for (FavTask stgr : ff) {
                for (Book book : bookList) {
                    String str = book.getIsbn();
                    if (str != null) {
                        if (str.equals(stgr.getIsbn())) {
                            OurBookList.add(book);
                            Log.d("TAG", "findCAtBook: " + OurBookList);
                        }
                    }
                }
            }
        }
        progressbar.setVisibility(View.GONE);
        if (OurBookList.isEmpty()){
            tv.setVisibility(View.VISIBLE);
        }else
        {
            tv.setVisibility(View.GONE);
        }
        favListAdapter.notifyDataSetChanged();
    }

    private void setupRvBooks() {
        if (favListAdapter == null) {
            favListAdapter = new FavListAdapter(getContext(), OurBookList);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(true);
            rv.setAdapter(favListAdapter);
        } else {
            favListAdapter.notifyDataSetChanged();
        }
    }

}