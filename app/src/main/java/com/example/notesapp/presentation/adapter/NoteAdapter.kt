package com.example.notesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.db.NoteEntity
import com.example.notesapp.databinding.ItemNoteBinding

class NoteAdapter(var datalist: List<NoteEntity>) : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    private lateinit var listener: OnItemClickListener


    inner class NoteHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.content
            binding.cardView.setOnClickListener {
                listener.onClicked(note)
            }
            binding.imgRemove.setOnClickListener {
                listener.onDeleteBtnClicked(note)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = ItemNoteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }


    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(datalist.get(position))
    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    interface OnItemClickListener {
        fun onClicked(note: NoteEntity)
        fun onDeleteBtnClicked(note: NoteEntity)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    val differ = AsyncListDiffer(this, diffCallback)


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem == newItem
            }
        }
    }


}
