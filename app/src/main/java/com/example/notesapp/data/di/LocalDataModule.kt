package com.example.notesapp.data.di

import NotesLocalDataSourceImpl
import com.example.notesapp.data.db.NoteDAO
import com.example.notesapp.data.repository.dataSource.NotesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(noteDAO: NoteDAO): NotesLocalDataSource {
        return NotesLocalDataSourceImpl(noteDAO)
    }

}













