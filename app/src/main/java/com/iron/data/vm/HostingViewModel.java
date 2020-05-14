package com.iron.data.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iron.data.dao.HostingDao;
import com.iron.data.entities.HostingEntity;
import com.iron.data.repo.HostingRepo;

import java.util.List;

public class HostingViewModel extends AndroidViewModel {

    private HostingRepo repo;
    private LiveData<List<HostingEntity>> allHosts;

    public HostingViewModel(@NonNull Application application) {
        super(application);
        repo = new HostingRepo(application);
        allHosts = repo.getAll();
    }


    public void insert(HostingEntity obj) {
        repo.insert(obj);
    }


    public void update(HostingEntity obj) {
        repo.update(obj);
    }


    public void delete(HostingEntity obj) {
        repo.delete(obj);
    }


    public void deleteAll() {
        repo.deleteAll();
    }


    public LiveData<List<HostingEntity>> getAll() {
        return allHosts;
    }
}
