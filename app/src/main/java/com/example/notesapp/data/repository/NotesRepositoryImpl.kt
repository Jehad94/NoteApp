package com.example.notesapp.data.repository

import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.data.db.NoteDAO
import com.example.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDAO: NoteDAO) : NotesRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
        return noteDAO.getNotes()
    }

    override fun getNotesByTitle(title: String): Flow<List<NoteEntity>> {
        return noteDAO.getListAllByName(title)
    }

    override suspend fun getNoteById(id: Int): NoteEntity {
        return noteDAO.getNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        return noteDAO.insertNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        return noteDAO.updateNotes(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        return noteDAO.deleteNote(note)
    }
}