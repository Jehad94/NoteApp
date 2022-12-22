package com.example.notesapp.domain.repository

import com.example.notesapp.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes(): Flow<List<NoteEntity>>
    fun getNotesByTitle(title: String): Flow<List<NoteEntity>>
    suspend fun getNoteById(id: Int): NoteEntity
    suspend fun insertNote(note: NoteEntity)
    suspend fun updateNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
}