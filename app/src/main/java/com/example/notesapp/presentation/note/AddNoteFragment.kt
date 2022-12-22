package com.example.notesapp.presentation.note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.R
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.common.BaseFragment
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.presentation.note.viewmodel.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>() {
    private val viewModel by viewModels<AddNoteViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            imgBack.setOnClickListener {
                navigate()
            }
            imgDone.setOnClickListener {
                saveNote()

            }

        }
    }


    private fun saveNote() {

        NoteEntity().apply {
            title = binding.etNoteTitle.text.toString()
            content = binding.etNoteDesc.text.toString()
        }.also { note ->
            viewModel.addNote(note)
        }
        navigate()
    }

    private fun navigate() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_addNoteFragment_to_noteListFragment)

    }

    override fun getViewBinding() = FragmentAddNoteBinding.inflate(layoutInflater)


}