package com.example.notesapp.presentation.note

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.common.BaseFragment
import com.example.notesapp.databinding.FragmentUpdateNoteBinding
import com.example.notesapp.presentation.note.viewmodel.UpdateNoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteFragment : BaseFragment<FragmentUpdateNoteBinding>() {
    private val viewModel by viewModels<UpdateNoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpPassedData()
        binding.apply {

            imgBack.setOnClickListener {
                navigate()
            }
            imgDone.setOnClickListener {
                viewModel.noteData.value?.let { updateNote(it) }

            }
        }

    }

    private fun setUpPassedData() {
        val args: UpdateNoteFragmentArgs by navArgs()
        val note = args.note
        note.id.also {
            if (it != -1) viewModel.setNoteId(it)
        }
        binding.etNoteTitle.setText(note.title)
        binding.etNoteDesc.setText(note.content)

    }

    private fun updateNote(note: NoteEntity) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        note.apply {
            title = binding.etNoteTitle.text.toString()
            content = binding.etNoteDesc.text.toString()
        }.also { note ->
            viewModel.updateNote(note)
        }
        navigate()
    }

    private fun navigate() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_updateNoteFragment_to_noteListFragment)

    }

    override fun getViewBinding() = FragmentUpdateNoteBinding.inflate(layoutInflater)

}