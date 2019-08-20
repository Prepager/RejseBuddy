package com.rejsebuddy.storage.recent;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecentDao {

    @Query("SELECT * FROM recent ORDER BY id DESC")
    List<Recent> all();

    @Insert
    void insert(Recent recent);

    @Query("DELETE FROM recent")
    void truncate();

}
