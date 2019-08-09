package com.rejsebuddy.storage.contact;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> all();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact find(int id);

    @Insert
    void insert(Contact... contacts);

    @Delete
    void delete(Contact contact);
}