package com.iron.data.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.iron.data.AppDatabase;
import com.iron.data.dao.ClientDao;
import com.iron.data.entities.ClientEntity;

import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
public class ClientRepo {

    private ClientDao dao;

    public ClientRepo(Application application) {
        dao = AppDatabase.getInstance(application).ClientDao();
    }

    public LiveData<List<ClientEntity>> getAll(){
        return dao.getAll();
    }

    public void insert(ClientEntity model){
        Executors.newSingleThreadExecutor().execute(()->dao.insert(model));
    }
}
