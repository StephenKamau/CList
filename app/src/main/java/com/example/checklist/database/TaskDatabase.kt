package com.example.checklist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
}

private lateinit var INSTANCE: TaskDatabase
fun getDatabase(context: Context): TaskDatabase {
    synchronized(TaskDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "tasks_db"
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}