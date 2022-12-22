package com.example.notesapp.presentation.note

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.common.BaseFragment
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.databinding.FragmentNoteListBinding
import com.example.notesapp.presentation.adapter.NoteAdapter
import com.example.notesapp.presentation.note.viewmodel.NoteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class NoteListFragment : BaseFragment<FragmentNoteListBinding>() {
    private val viewModel by viewModels<NoteListViewModel>()
    private lateinit var adapter: NoteAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNoteLis()
        binding.btnaddNote.setOnClickListener {
            navigate(true)
        }
        search()
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.onSearchTextChanged(p0.toString())
                return true
            }
        })
    }


    private val onClicked = object : NoteAdapter.OnItemClickListener {
        override fun onClicked(note: NoteEntity) {

            navigate(false, note)
        }

        override fun onDeleteBtnClicked(note: NoteEntity) {
            deleteNote(note)
        }
    }

    private fun deleteNote(note: NoteEntity) {
        viewModel.deleteNote(note)
    }

    private fun observeNoteLis() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listOfNotes.observe(requireActivity()) {
                setUpRecyclerView(it)
            }
        }

    }

    private fun setUpRecyclerView(notesList: List<NoteEntity>) {
        binding.apply {
            rvNotes.layoutManager = LinearLayoutManager(requireContext())
            adapter = NoteAdapter(notesList)
            rvNotes.adapter = adapter
            adapter.setOnClickListener(onClicked)

        }

    }


    private fun navigate(isNewNote: Boolean, note: NoteEntity? = null) {
        if (isNewNote) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_noteListFragment_to_addNoteFragment)

        } else {
            val bundle = Bundle().apply {
                putSerializable("note", note)
            }
            findNavController().navigate(
                R.id.action_noteListFragment_to_updateNoteFragment,
                bundle
            )
        }
    }

    override fun getViewBinding() = FragmentNoteListBinding.inflate(layoutInflater)

}