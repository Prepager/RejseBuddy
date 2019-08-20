package com.rejsebuddy.storage.recent;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rejsebuddy.storage.contact.Contact;

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
