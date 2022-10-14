package com.example.booknet.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.booknet.data.db.entity.BookEntity

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBooks(list: List<BookEntity>)

    @Query("DELETE FROM table_book WHERE id NOT IN (:listId)")
    fun deleteOldBooksById(listId: List<Int>)

    @Transaction
    fun updateBooks(list: List<BookEntity>) {
        insertBooks(list)
        val listId: List<Int> = list.map { it.id }
        deleteOldBooksById(listId)
    }

    @Query("SELECT * FROM table_book WHERE id = :id")
    fun booksByIdLiveData(id: String): LiveData<List<BookEntity>>

    @Query("SELECT * FROM table_book ")
    fun booksLiveData(): LiveData<List<BookEntity>>
}