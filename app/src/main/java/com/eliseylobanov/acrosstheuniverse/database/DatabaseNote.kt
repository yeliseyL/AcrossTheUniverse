package com.eliseylobanov.acrosstheuniverse.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eliseylobanov.acrosstheuniverse.entities.Note

@Entity(tableName = "notes_table")
class DatabaseNote {
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L
    var noteDate: String = ""
    var titleText: String = ""
    var noteText: String = ""
}

internal val DatabaseNote.toNote: Note
    get() = Note(
        this.noteId,
        this.titleText,
        this.noteText,
        this.noteDate,
    )