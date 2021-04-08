package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.app.Application
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.acrosstheuniverse.database.getDatabase
import com.eliseylobanov.acrosstheuniverse.databinding.ItemNoteBinding
import com.eliseylobanov.acrosstheuniverse.entities.Note
import com.eliseylobanov.acrosstheuniverse.repository.NotesRepository

class NotesAdapter(private val onClickListener: OnClickListener, private val viewModel: NotesViewModel) :
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(DiffCallback), ItemTouchHelperAdapter {

    private val newList = arrayListOf<Note>()

    class NotesViewHolder(private var binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root), ItemTouchHelperViewHolder {
        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }

        override fun onItemSelected() {
            binding.noteView.setCardBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            binding.noteView.setCardBackgroundColor(0)
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

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        newList.clear()
        newList.addAll(currentList)
        newList.removeAt(fromPosition).apply {
            newList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        viewModel.deleteNote(currentList[position])
        notifyItemRemoved(position)
    }
}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewHolder {

    fun onItemSelected()

    fun onItemClear()
}