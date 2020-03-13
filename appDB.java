package com.example.lab1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {flash.class}, version = 1)
public abstract class appDB extends RoomDatabase {
    public abstract flashDao flashDao();
}
