package com.eliseylobanov.acrosstheuniverse.entities

import android.os.Parcelable
import com.eliseylobanov.acrosstheuniverse.database.DatabaseNote
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note(
    var noteId: Long = 0L,
    var titleText: String = "",
    var noteText: String = "",
    var noteDate: String = ""
) : Parcelable

internal val Note.toDatabaseNote: DatabaseNote
    get() {
        val noteEntity = DatabaseNote()
        if (this.noteId != 0L) {
            noteEntity.noteId = this.noteId
        }
        noteEntity.noteDate = this.noteDate
        noteEntity.titleText = this.titleText
        noteEntity.noteText = this.noteText
        return noteEntity
    }