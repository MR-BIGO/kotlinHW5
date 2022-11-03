package com.example.bookappsqlite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "Name") val name: String?,
    @ColumnInfo(name = "Author") val author: String?,
    @ColumnInfo(name = "Year") val year: Int?,
)
