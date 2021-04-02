package com.books.shelf.ui.dashboard;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.books.shelf.R;
import com.books.shelf.adapter.CatAdapter;
import com.books.shelf.room.Task;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private List<Task> CAtList = new ArrayList<>();
    private CatAdapter adapter;
    private RecyclerView rv;

    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rv = root.findViewById(R.id.rvCats);

        dashboardViewModel.getAllCats().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable final List<Task> cats) {
                CAtList.addAll(cats);
                adapter.notifyDataSetChanged();
            }
        });
        setupRecyclerView();
        return root;
    }
    private void setupRecyclerView() {
        if (adapter == null) {
            adapter = new CatAdapter( CAtList,getContext());
            rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
            rv.setAdapter(adapter);
            rv.setItemAnimator(new DefaultItemAnimator());
            rv.setNestedScrollingEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}