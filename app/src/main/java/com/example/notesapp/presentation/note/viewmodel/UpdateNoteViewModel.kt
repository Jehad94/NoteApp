package com.example.notesapp.presentation.note.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.usecase.GetNoteUseCase
import com.example.notesapp.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateNoteViewModel @Inject constructor(
    private val getNote: GetNoteUseCase,
    private val updateNote: UpdateNoteUseCase
) : ViewModel() {
    var note: MutableLiveData<NoteEntity> = MutableLiveData()

    private val noteId = MutableStateFlow<Int?>(null)

    val noteData = noteId.flatMapLatest {
        val note = it?.let { getNote.execute(it) }
        flowOf(note)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun setNoteId(id: Int) = viewModelScope.launch {
        noteId.emit(id)
    }


    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            updateNote.execute(note)
        }
    }
}