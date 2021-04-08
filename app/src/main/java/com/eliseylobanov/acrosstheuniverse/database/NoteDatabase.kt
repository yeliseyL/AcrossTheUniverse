package com.eliseylobanov.acrosstheuniverse.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface NoteDao {
    @Query("SELECT * from notes_table WHERE noteId = :key")
    suspend fun get(key: Long): DatabaseNote?

    @Delete
    suspend fun delete(note: DatabaseNote)

    @Query("DELETE FROM notes_table")
    suspend fun clear()

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<MutableList<DatabaseNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: DatabaseNote)

    @Update
    suspend fun update(note: DatabaseNote)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<DatabaseNote>)
}

@Database(entities = [DatabaseNote::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}

private lateinit var INSTANCE: NoteDatabase

fun getDatabase(context: Context): NoteDatabase {
    synchronized(NoteDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                NoteDatabase::class.java,
                "notes").build()
        }
    }
    return INSTANCE
}