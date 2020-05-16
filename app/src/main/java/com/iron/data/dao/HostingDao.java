package com.iron.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iron.data.entities.HostingEntity;

import java.util.List;

@Dao
public interface HostingDao {

    @Insert
    void insert(HostingEntity obj);

    @Update
    void update(HostingEntity obj);

    @Delete
    void delete(HostingEntity obj);

    @Query("DELETE FROM hosting_table")
    void deleteAll();

    @Query("SELECT * FROM hosting_table ORDER BY clientName ASC")
    LiveData<List<HostingEntity>> getAll();


}
