package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.app.Application
import androidx.lifecycle.*
import com.eliseylobanov.acrosstheuniverse.database.getDatabase
import com.eliseylobanov.acrosstheuniverse.entities.Note
import com.eliseylobanov.acrosstheuniverse.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val _navigateToSelectedNote = MutableLiveData<Note?>()
    val navigateToSelectedNote: LiveData<Note?>
        get() = _navigateToSelectedNote

    private val database = getDatabase(application)
    private val notesRepository = NotesRepository(database)

    val notes = notesRepository.notes

    fun displayNote(note: Note) {
        _navigateToSelectedNote.value = note
    }

    fun displayNoteComplete() {
        _navigateToSelectedNote.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NotesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}