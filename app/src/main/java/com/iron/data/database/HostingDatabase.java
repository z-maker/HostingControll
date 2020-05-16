package com.iron.data.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iron.data.dao.HostingDao;
import com.iron.data.entities.HostingConstants;
import com.iron.data.entities.HostingEntity;

@Database(entities = {HostingEntity.class}, version = 1)
public abstract class HostingDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "hosting_control";
    private static HostingDatabase instance;

    public abstract HostingDao hostingDao();

    public static synchronized HostingDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HostingDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsync(instance).execute();
        }
    };

    private static class PopulateAsync extends AsyncTask<Void,Void,Void>{

        private HostingDao dao;

        public PopulateAsync(HostingDatabase db) {
            this.dao = db.hostingDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));
            dao.insert(new HostingEntity("Vinos america 1","vinosamerica.com",
                    HostingConstants.DOMAIN_ACTIVE,"29-03-2019","29-03-2020","29-13-2021",
                    HostingConstants.MONTHLY_1,HostingConstants.PAYMENT_COMPLETE,200,250,"04-06-2020",
                    "04-06-2020",HostingConstants.HOSING_ACTIVE,"Undefined",
                    "03-05-2200","01-05-2000",System.currentTimeMillis()));


            return null;
        }
    }

}
