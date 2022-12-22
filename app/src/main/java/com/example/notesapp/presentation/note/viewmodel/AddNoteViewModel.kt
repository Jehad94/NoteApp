package com.example.notesapp.presentation.note.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.usecase.AddNoteUseCase
import com.example.notesapp.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val noteUseCases: AddNoteUseCase
) : ViewModel() {

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            noteUseCases.execute(note)
        }
    }
}