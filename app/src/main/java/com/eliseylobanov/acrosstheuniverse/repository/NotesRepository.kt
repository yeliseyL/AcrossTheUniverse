package com.eliseylobanov.acrosstheuniverse.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.eliseylobanov.acrosstheuniverse.database.DatabaseNote
import com.eliseylobanov.acrosstheuniverse.database.NoteDatabase
import com.eliseylobanov.acrosstheuniverse.database.toNote
import com.eliseylobanov.acrosstheuniverse.entities.Note

class NotesRepository(private val database: NoteDatabase) {
    var notes: MutableLiveData<ArrayList<Note>> =
        Transformations.map(database.noteDao.getAllNotes()) {list ->
            list.map { it.toNote }
        } as MutableLiveData<ArrayList<Note>>

    suspend fun addOrReplace(note: DatabaseNote) {
        database.noteDao.insert(note)
    }

    suspend fun delete(id: Long) {
        database.noteDao.delete(id)
    }
}