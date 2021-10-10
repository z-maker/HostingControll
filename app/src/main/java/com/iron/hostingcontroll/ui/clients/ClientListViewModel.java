package com.iron.hostingcontroll.ui.clients;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.iron.data.entities.ClientEntity;
import com.iron.data.repo.ClientRepo;

import java.util.List;

public class ClientListViewModel extends AndroidViewModel {

    private ClientRepo repo;

    private LiveData<List<ClientEntity>> liveData;

    public ClientListViewModel(@NonNull Application application) {
        super(application);
        repo = new ClientRepo(application);
        liveData = repo.getAll();
    }

    public LiveData<List<ClientEntity>> getData() {
        return liveData;
    }

    public void getAll(){
        liveData = repo.getAll();
    }

    public void insert(ClientEntity entity){
        repo.insert(entity);
    }

}