package com.example.notesapp.domain.usecase

import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesUseCase  @Inject constructor(private val notesRepository: NotesRepository) {

    suspend fun execute(): Flow<List<NoteEntity>> {
        return notesRepository.getNotes()
    }
}