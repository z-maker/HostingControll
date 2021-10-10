package com.iron.data.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.iron.data.entities.ClientEntity;

import java.util.List;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
@Dao
public interface ClientDao {

    @Query("select * from hc_clients")
    LiveData<List<ClientEntity>> getAll();

    @Query("select * from hc_clients where idClient = :id")
    LiveData<ClientEntity> getById(@NonNull int id);

    @Insert(onConflict = REPLACE)
    void insert(@NonNull ClientEntity entity);

    @Update(onConflict = REPLACE)
    void update(@NonNull ClientEntity entity);

    @Delete
    void delete(@NonNull ClientEntity entity);

    @Query(" delete from hc_clients ")
    void deleteAll();

}
