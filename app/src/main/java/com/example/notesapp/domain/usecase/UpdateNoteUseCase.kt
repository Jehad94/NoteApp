package com.example.notesapp.domain.usecase

import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend fun execute(note: NoteEntity) {
        notesRepository.updateNote(note)
    }
}