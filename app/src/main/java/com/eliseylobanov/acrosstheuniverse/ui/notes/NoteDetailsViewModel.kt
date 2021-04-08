package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.eliseylobanov.acrosstheuniverse.database.getDatabase
import com.eliseylobanov.acrosstheuniverse.entities.Note
import com.eliseylobanov.acrosstheuniverse.entities.toDatabaseNote
import com.eliseylobanov.acrosstheuniverse.getToday
import com.eliseylobanov.acrosstheuniverse.repository.NotesRepository
import kotlinx.coroutines.launch

class NoteDetailsViewModel(application: Application, var note: Note?) : ViewModel() {

    var noteId: Long = 0
    var noteDate = ""
    var titleText = ""
    var noteText = ""

    private val database = getDatabase(application)
    private val notesRepository = NotesRepository(database)

    init {
        noteDate = getToday()
        note?.let {
            noteId = it.noteId
            titleText = it.titleText
            noteText = it.noteText
        }
    }

    private fun updateFields(note: Note) {
        note.noteId = noteId
        note.noteDate = getToday()
        note.titleText = titleText
        note.noteText = noteText
    }

    fun createNote() {
        viewModelScope.launch {
            val newNote = Note()
            try {
                newNote.let {
                    updateFields(it)
                    notesRepository.addOrReplace(it.toDatabaseNote)
                }
            } catch (th: Throwable) {
                Log.e("PictureOfDay", "Network error")
            }
        }
    }

    class Factory(val app: Application, val note: Note?) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NoteDetailsViewModel(app, note) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}