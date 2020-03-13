package com.example.lab1;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface flashDao {
    @Query("SELECT * FROM flash")
    List<flash> getAll();

    @Insert
    void insertAll(flash... flashcards);

    @Delete
    void delete(flash flashcard);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(flash flashcard);
}
