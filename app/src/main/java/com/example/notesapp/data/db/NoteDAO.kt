package com.example.notesapp.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM note_table where title LIKE :name or LOWER(title) LIKE '%' || LOWER(:name) || '%'")
    fun getListAllByName(name: String): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNotes(note: NoteEntity)
}