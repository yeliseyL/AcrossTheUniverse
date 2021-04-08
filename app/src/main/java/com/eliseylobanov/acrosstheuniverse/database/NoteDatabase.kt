package com.eliseylobanov.acrosstheuniverse.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * from notes_table WHERE noteId = :key")
    suspend fun get(key: Long): DatabaseNote?

    @Query("DELETE FROM notes_table WHERE noteId = :key")
    suspend fun delete(key: Long)

    @Query("DELETE FROM notes_table")
    suspend fun clear()

    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<DatabaseNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: DatabaseNote)

    @Update
    suspend fun update(note: DatabaseNote)
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