package com.example.bookappsqlite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {


    @Query("SELECT * FROM books_table")
    fun getBooks(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(book: Book)

    @Delete
    suspend fun delete(book: Book)
}