package com.rejsebuddy.storage.contact;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> all();

    @Query("SELECT * FROM contact WHERE id = :id")
    Contact find(int id);

    @Insert
    void insert(Contact contact);

    @Insert
    void insertMany(List<Contact> contacts);

    @Update
    void update(Contact contact);

    @Update
    void updateMany(List<Contact> contacts);

    @Delete
    void delete(Contact contact);

    @Query("DELETE FROM contact")
    void truncate();

}