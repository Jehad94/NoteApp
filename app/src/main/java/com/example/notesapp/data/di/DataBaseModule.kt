package com.example.notesapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.notesapp.data.db.NoteDAO
import com.example.notesapp.data.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDAO {
        return noteDatabase.getNoteDAO()
    }

    @Singleton
    @Provides
    fun provideNotesDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }





}