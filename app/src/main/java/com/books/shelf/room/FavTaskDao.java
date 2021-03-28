package com.books.shelf.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavTaskDao {

    @Query("SELECT * FROM my_favs")
    List<FavTask> getAllFavs();

    @Insert
    void insertFavs(FavTask favTask);

    @Query("DELETE FROM my_favs")
    void deleteAllFavs();

    @Query("DELETE FROM my_favs WHERE bookuid = :isbn")
    void removeFavs(String isbn);


}
