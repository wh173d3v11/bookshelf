package com.books.shelf.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.books.shelf.network.ApiResponse;
import com.books.shelf.room.DatabaseClient;
import com.books.shelf.room.FavTask;
import com.books.shelf.room.FavTaskDao;

import java.util.List;

public class FavRepository {
    private FavTaskDao favTaskDao;
    private final List<FavTask> mfavlist;


    public FavRepository(Application application){

        DatabaseClient db = DatabaseClient.getInstance(application);
        favTaskDao = db.getAppDatabase().favTaskDao();
        mfavlist=favTaskDao.getAllFavs();
    }
    public MutableLiveData<List<FavTask>> getAllFavs(){
        final MutableLiveData<List<FavTask>> mfavlistLive = new MutableLiveData<>();
        mfavlistLive.setValue(mfavlist);
        return mfavlistLive;
    }
}
