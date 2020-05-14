package com.iron.data.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.iron.data.dao.HostingDao;
import com.iron.data.database.HostingDatabase;
import com.iron.data.entities.HostingEntity;

import java.util.List;

public class HostingRepo{

    private HostingDao hostingDao;
    private LiveData<List<HostingEntity>> allHosts;

    public HostingRepo(Application application){

        HostingDatabase database = HostingDatabase.getInstance(application);
        hostingDao = database.hostingDao();
        allHosts = hostingDao.getAll();

    }

    
    public void insert(HostingEntity obj) {
        new InsertAsync(hostingDao).execute(obj);
    }

    
    public void update(HostingEntity obj) {
        new UpdateAsync(hostingDao).execute(obj);
    }

    
    public void delete(HostingEntity obj) {
        new DeleteAsync(hostingDao).execute(obj);
    }

    
    public void deleteAll() {
        new DeleteAllAsync(hostingDao).execute();
    }

    
    public LiveData<List<HostingEntity>> getAll() {
        return allHosts;
    }

    private static class InsertAsync extends AsyncTask<HostingEntity, Void, Void>{

        private HostingDao hostingDao;

        public InsertAsync(HostingDao hostingDao) {
            this.hostingDao = hostingDao;
        }

        @Override
        protected Void doInBackground(HostingEntity... hostingEntities) {
            hostingDao.insert(hostingEntities[0]);
            return null;
        }
    }

    private static class UpdateAsync extends AsyncTask<HostingEntity, Void, Void>{

        private HostingDao hostingDao;

        public UpdateAsync(HostingDao hostingDao) {
            this.hostingDao = hostingDao;
        }

        @Override
        protected Void doInBackground(HostingEntity... hostingEntities) {
            hostingDao.update(hostingEntities[0]);
            return null;
        }
    }

    private static class DeleteAsync extends AsyncTask<HostingEntity, Void, Void>{

        private HostingDao hostingDao;

        public DeleteAsync(HostingDao hostingDao) {
            this.hostingDao = hostingDao;
        }

        @Override
        protected Void doInBackground(HostingEntity... hostingEntities) {
            hostingDao.delete(hostingEntities[0]);
            return null;
        }
    }

    private static class DeleteAllAsync extends AsyncTask<Void, Void, Void>{

        private HostingDao hostingDao;

        public DeleteAllAsync(HostingDao hostingDao) {
            this.hostingDao = hostingDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            hostingDao.deleteAll();
            return null;
        }
    }
}
