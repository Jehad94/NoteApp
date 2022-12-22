package com.example.notesapp.data.repository.dataSource

import com.example.notesapp.data.db.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {
    suspend fun saveNoteToDB(note: NoteEntity)
    fun getSavedNotes(): Flow<List<NoteEntity>>
    fun geteNotsByTitle(title: String): Flow<List<NoteEntity>>
    suspend fun getNote(id: Int): NoteEntity
    suspend fun deleteNoteFromDB(note: NoteEntity)
    suspend fun updateNoteFromDB(note: NoteEntity)


}