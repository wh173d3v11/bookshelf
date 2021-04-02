package com.books.shelf.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.books.shelf.repo.BookRepository;
import com.books.shelf.network.ApiResponse;
import com.books.shelf.room.DatabaseClient;
import com.books.shelf.room.Task;


public class SplashViewModel extends AndroidViewModel {

    private MediatorLiveData<ApiResponse> mApiResponse;
    private final BookRepository mApiRepo;

    public SplashViewModel(Application application) {
        super(application);
        mApiResponse = new MediatorLiveData<>();
        mApiRepo = new BookRepository();
    }

    public MutableLiveData<ApiResponse> getData() {
        mApiResponse.addSource(mApiRepo.getBooks(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(@Nullable ApiResponse apiResponse)            {
                mApiResponse.setValue(apiResponse);
            }
        });
        return mApiResponse;
    }
    private void saveTask(String str) {
        if (str.isEmpty()) {
            return;
        }
        @SuppressLint("StaticFieldLeak")
        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setCategory(str);


                //adding to database
                DatabaseClient.getInstance(getApplication()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                //  Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        if (!str.isEmpty()) {
            st.execute();
        }
    }
}
