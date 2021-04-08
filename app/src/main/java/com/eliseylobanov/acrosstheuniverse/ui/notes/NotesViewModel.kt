package com.eliseylobanov.acrosstheuniverse.ui.notes

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.eliseylobanov.acrosstheuniverse.database.getDatabase
import com.eliseylobanov.acrosstheuniverse.entities.Note
import com.eliseylobanov.acrosstheuniverse.entities.toDatabaseNote
import com.eliseylobanov.acrosstheuniverse.repository.NotesRepository
import kotlinx.coroutines.launch

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

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try { notesRepository.delete(note.toDatabaseNote)
            } catch (th: Throwable) {
                Log.e("PictureOfDay", "Network error")
            }
        }
    }

    fun updateNotes(notes: List<Note>) {
        viewModelScope.launch {
            try { notesRepository.updateAll(notes)
            } catch (th: Throwable) {
                Log.e("PictureOfDay", "Network error")
            }
        }
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