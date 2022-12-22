package com.example.notesapp.domain.usecase

import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.repository.NotesRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {


    suspend fun execute(id: Int): NoteEntity {
        return notesRepository.getNoteById(id)
    }
}