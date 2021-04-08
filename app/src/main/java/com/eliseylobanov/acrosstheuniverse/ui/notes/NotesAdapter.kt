package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.databinding.ItemNoteBinding
import com.eliseylobanov.acrosstheuniverse.entities.Note

class NotesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(DiffCallback) {

    class NotesViewHolder(private var binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(note)
        }
        holder.bind(note)
    }

    class OnClickListener(val clickListener: (note: Note) -> Unit) {
        fun onClick(note: Note) = clickListener(note)
    }
}