package com.example.notesapp.presentation.note.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.domain.usecase.DeleteNoteUseCase
import com.example.notesapp.domain.usecase.GetNotesByTitleUseCase
import com.example.notesapp.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getNotes: GetNotesUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotesByTitle: GetNotesByTitleUseCase,
) : ViewModel() {


    var listOfNotes: MutableLiveData<List<NoteEntity>> = MutableLiveData()

    init {

        getNotes()
    }


    private fun getNotes() {
        viewModelScope.launch {
            getNotes.execute().collect { list ->
                if (list.isNotEmpty()) {
                    listOfNotes.value = list
                }

            }
        }
    }

    fun deleteNote(note: NoteEntity) = viewModelScope.launch {
        deleteNote.execute(note)
    }

    fun onSearchTextChanged(query: String) = viewModelScope.launch {
        getNotesByTitle.execute(query).collect { list ->
            if (list.isNotEmpty()) {
                listOfNotes.value = list
            }

        }
//        listOfNotes.value?.map {
//            if (it.title?.contains(query, ignoreCase = true) == true) {
//                listOfNotes.value=
//            }
//
//
//        }
    }

}