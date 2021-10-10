package com.iron.v1.data.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.iron.v1.data.dao.HostingDao;
import com.iron.v1.data.entities.HostingEntity;
import com.iron.v1.data.repo.HostingRepo;

import java.util.List;

public class HostingViewModel extends AndroidViewModel implements HostingDao {

    private HostingRepo repo;
    private LiveData<List<HostingEntity>> allHosts;

    public HostingViewModel(@NonNull Application application) {
        super(application);
        repo = new HostingRepo(application);
        allHosts = repo.getAll();
    }

    public void insert(List<HostingEntity> list){
        for (HostingEntity obj:list) {
            repo.insert(obj);
        }
    }

    public void update(List<HostingEntity> list){
        for (HostingEntity obj:list) {
            repo.update(obj);
        }
    }

    @Override
    public void insert(HostingEntity obj) {
        repo.insert(obj);
    }

    @Override
    public void update(HostingEntity obj) {
        repo.update(obj);
    }

    @Override
    public void delete(HostingEntity obj) {
        repo.delete(obj);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }

    @Override
    public LiveData<List<HostingEntity>> getAll() {
        return allHosts;
    }
}
