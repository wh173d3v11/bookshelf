package com.books.shelf.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.books.shelf.R;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.network.Book;
import com.books.shelf.adapter.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ArrayList<Book> BookList = new ArrayList<Book>();
    private CatAdapter adapter;
    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rv = root.findViewById(R.id.rvCats);

        dashboardViewModel.getData().observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
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
                    Toast.makeText(getContext(), "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Error is " + e.getLocalizedMessage());

                }
            }});
        setupRecyclerView();
        return root;
    }

    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new CatAdapter(getContext(), BookList);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

}