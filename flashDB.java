package com.example.lab1;

import android.content.Context;

import java.util.List;

import androidx.room.Room;

public class flashDB {
    private final appDB db;

    flashDB(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                appDB.class, "flashcard-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public List<flash> getAllCards() {
        return db.flashDao().getAll();
    }

    public void insertCard(flash flashcard) {
        db.flashDao().insertAll(flashcard);
    }

    public void deleteCard(String flashcardQuestion) {
        List<flash> allCards = db.flashDao().getAll();
        for (flash f : allCards) {
            if (f.getQuestion().equals(flashcardQuestion)) {
                db.flashDao().delete(f);
            }
        }
    }

    public void updateCard(flash flashcard) {
        db.flashDao().update(flashcard);
    }
}
