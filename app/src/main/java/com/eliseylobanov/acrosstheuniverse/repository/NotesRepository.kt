package com.eliseylobanov.acrosstheuniverse.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.eliseylobanov.acrosstheuniverse.database.DatabaseNote
import com.eliseylobanov.acrosstheuniverse.database.NoteDatabase
import com.eliseylobanov.acrosstheuniverse.database.toNote
import com.eliseylobanov.acrosstheuniverse.entities.Note
import com.eliseylobanov.acrosstheuniverse.entities.toDatabaseNote

class NotesRepository(private val database: NoteDatabase) {
    var notes: MutableLiveData<MutableList<Note>> =
        Transformations.map(database.noteDao.getAllNotes()) {list ->
            list.map { it.toNote }
        } as MutableLiveData<MutableList<Note>>

    suspend fun addOrReplace(note: DatabaseNote) {
        database.noteDao.insert(note)
    }

    suspend fun delete(note: DatabaseNote) {
        database.noteDao.delete(note)
    }

    suspend fun clear() {
        database.noteDao.clear()
    }

    suspend fun updateAll(notes: List<Note>) {
        val notesDatabase = notes.map{
            it.toDatabaseNote
        }
        database.noteDao.insertAll(notesDatabase)
    }

    suspend fun update(note: Note) {
        database.noteDao.update(note.toDatabaseNote)
    }
}