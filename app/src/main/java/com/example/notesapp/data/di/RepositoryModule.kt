package com.example.notesapp.data.di

import com.example.notesapp.data.db.NoteDAO
import com.example.notesapp.data.repository.NotesRepositoryImpl
import com.example.notesapp.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideNotesRepository(
        noteDAO: NoteDAO

    ): NotesRepository {
        return NotesRepositoryImpl(noteDAO)
    }


}