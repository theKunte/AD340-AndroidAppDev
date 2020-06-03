package com.example.helloworld.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.helloworld.entity.Settings;

import java.util.List;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM settings WHERE id = (:id)")
    LiveData<Settings> loadById(Integer id);

    @Update
    void updateSettings(Settings settings);

//    @Query("SELECT * FROM settings WHERE id = (:id)")
//    void updateMinAge(Integer id, Integer minAge);
//
//    @Query("SELECT * FROM settings WHERE id = (:id)")
//    void updateMaxAge(Integer id, Integer maxAge);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSettings(Settings settings);

    @Delete
    void delete(Settings settings);
}