package com.example.booknet.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booknet.data.db.dao.BooksDao
import com.example.booknet.data.db.entity.BookEntity

private const val DB_NAME = "Data.db"
private const val DB_VERSION = 1


@Database(
    entities = [BookEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao
}

// For Singleton instantiation
@Volatile
private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = buildDatabase(context)
        }
    }
    return INSTANCE
}

private fun buildDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}
