package com.iron.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iron.data.dao.ClientDao;
import com.iron.data.entities.ClientEntity;
import com.iron.data.entities.ProductEntity;
import com.iron.data.entities.ServiceEntity;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
@Database(entities = {
        ClientEntity.class,
        ProductEntity.class,
        ServiceEntity.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_database";

    private static AppDatabase instance;

    public abstract ClientDao ClientDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static final RoomDatabase.Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

}
